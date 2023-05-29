package com.ratracks.infra.services;

import com.ratracks.domain.contracts.services.AbstractShippingService;
import com.ratracks.infra.entities.TokenShipping;
import com.ratracks.infra.repositories.TokenShippingRepository;
import com.ratracks.infra.schemas.TokenShippingSchema;
import com.ratracks.utils.localDateTime.ValidateTime;
import com.ratracks.utils.string.UrlBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CorreiosShippingService extends AbstractShippingService {

    private final Environment env;
    private final RestTemplate restTemplate;
    private final UrlBuilder urlBuilder;
    private final TokenShippingRepository repository;

    @Autowired
    private CorreiosShippingService(RestTemplate restTemplate, Environment env, UrlBuilder urlBuilder, TokenShippingRepository repository) {
        this.restTemplate = restTemplate;
        this.env = env;
        this.urlBuilder = urlBuilder;
        this.repository = repository;
    }


    private String getApiTokenValidOnDatabase() throws Exception {
        Optional<TokenShippingSchema> tokenShippingSchema = repository.getTokenShippingByName("correios");
        String token;

        if (tokenShippingSchema.isPresent()) {
            TokenShippingSchema tokenShipping = tokenShippingSchema.get();
            boolean tokenIsGreaterThanThirtyMinutes = ValidateTime.isGreaterThanThirtyMinutes(tokenShipping.getUpdatedAt());
            if (tokenIsGreaterThanThirtyMinutes) {
                tokenShipping.setToken(generateToken());
                tokenShipping.setUpdatedAt(LocalDateTime.now());
                repository.save(tokenShipping);
            }
            return tokenShipping.getToken();
        } else {
            token = generateToken();
            TokenShipping tokenShipping = new TokenShipping("correios", token);
            repository.create(tokenShipping);
        }
        return token;
    }

    private String generateToken() throws Exception {
        try {
            String apiUrl = urlBuilder.buildCorreiosApiUrl("app-validation");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("User-Agent", env.getProperty("api.correios.user.agent"));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("requestToken", env.getProperty("api.correios.token"));
            HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            String responseBody = responseEntity.getBody();
            JSONObject jsonObjectResponseBody = new JSONObject(responseBody);

            return jsonObjectResponseBody.getString("token");
        } catch (HttpStatusCodeException ex) {
            throw new Exception("Error when generate token to Correios API: " + ex.getMessage());
        } catch (Exception ex) {
            throw new Exception("Unexpected error calling Correios API: " + ex.getMessage());
        }
    }

    @Override
    public String getDetailsByTrackingCode(String trackCode) throws Exception {
        StringBuilder path = new StringBuilder();
        path.append("sro-rastro/");
        path.append(trackCode);
        String apiUrl = urlBuilder.buildCorreiosApiUrl(path.toString());

        String token = getApiTokenValidOnDatabase();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", env.getProperty("api.correios.user.agent"));
        headers.set("app-check-token", token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            return responseEntity.getBody();
        } catch (HttpStatusCodeException ex) {
            throw new Exception("Error get information of track Code in Correios API: " + ex.getMessage());
        } catch (Exception ex) {
            throw new Exception("Unexpected error calling Correios API: " + ex.getMessage());
        }
    }
}
