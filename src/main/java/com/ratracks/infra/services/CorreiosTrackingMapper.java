package com.ratracks.infra.services;

import com.ratracks.domain.contracts.services.TrackingDetails;
import com.ratracks.domain.contracts.services.TrackingEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CorreiosTrackingMapper {
    public TrackingDetails mapJsonToTrackingDetails(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray objetosArray = jsonObject.getJSONArray("objetos");

        if (objetosArray.length() == 0) {
            throw new Exception("Nenhum objeto encontrado.");
        }

        JSONObject objeto = objetosArray.getJSONObject(0);
        TrackingDetails trackingDetails = new TrackingDetails();

        trackingDetails.setTrackCode(objeto.getString("codObjeto"));
        trackingDetails.setType(objeto.getJSONObject("tipoPostal").getString("categoria"));
        trackingDetails.setDescriptionType(objeto.getJSONObject("tipoPostal").getString("descricao"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String expectedDateStr = objeto.optString("dtPrevista");
        Date expectedDate = null;
        if (!expectedDateStr.isEmpty()) {
            expectedDate = dateFormat.parse(expectedDateStr);
        }
        trackingDetails.setExpectedDate(expectedDate);

        JSONArray eventosArray = objeto.getJSONArray("eventos");
        List<TrackingEvent> events = new ArrayList<>();

        for (int i = 0; i < eventosArray.length(); i++) {
            JSONObject evento = eventosArray.getJSONObject(i);
            TrackingEvent trackingEvent = new TrackingEvent();

            String eventDateStr = evento.getString("dtHrCriado");
            Date eventDate = dateFormat.parse(eventDateStr);
            trackingEvent.setEventDate(eventDate);

            trackingEvent.setDescription(evento.getString("descricao"));

            JSONObject unidade = evento.getJSONObject("unidade");
            if (Objects.equals(unidade.getString("tipo"), "País")) {
                trackingEvent.setCity(unidade.optString("nome"));
            } else {
                trackingEvent.setCity(unidade.getJSONObject("endereco").optString("cidade"));
            }
            trackingEvent.setType(unidade.getString("tipo"));
            trackingEvent.setUf(unidade.getJSONObject("endereco").optString("uf"));

            events.add(trackingEvent);
        }

        trackingDetails.setEvents(events);

        return trackingDetails;
    }
}
