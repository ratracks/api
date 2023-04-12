package com.ratracks.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ratracks.exceptions.TrackingCodeException;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import org.junit.jupiter.api.Test;

public class TrackingTest {

    @Test
    public void newTrackingInstance() {
        String productName = "TestName";
        String trackingCode = "AA123456789BR";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;

        Tracking entity = new Tracking(productName, trackingCode);

        assertEquals(productName, entity.getProductName());
        assertEquals(trackingCode, entity.getTrackingCode());
        assertEquals(status, entity.getStatus());
        assertEquals(transporter, entity.getTransporter());
    }

    @Test
    public void newTrackingInstanceWithNullTrackingCode() {
        String productName = "TestName";
        String trackingCode = "";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;

        assertThrows(TrackingCodeException.class, () -> new Tracking(productName, trackingCode));
    }

    @Test
    public void newTrackingInstanceWithInvalidTrackingCode() {
        String productName = "TestName";
        String trackingCode = "INVALIDCODE";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;

        assertThrows(TrackingCodeException.class, () -> new Tracking(productName, trackingCode));
    }
}
