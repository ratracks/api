package com.ratracks.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ratracks.domain.entities.tracking.Tracking;
import com.ratracks.domain.entities.tracking.valueobjects.TrackingCode;
import com.ratracks.exceptions.TrackingCodeException;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrackingTest {

    @Test
    public void newTrackingInstance() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        String productName = "TestName";
        String trackingCode = "AA123456789BR";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;
        UUID userId = UUID.randomUUID();

        Tracking entity = new Tracking(id, createdAt, updatedAt, productName, new TrackingCode(trackingCode), transporter, status, userId);

        assertEquals(productName, entity.getProductName());
        assertEquals(trackingCode, entity.getTrackingCode().getCode());
        assertEquals(status, entity.getStatus());
        assertEquals(transporter, entity.getTransporter());
    }

    @Test
    public void newTrackingInstanceWithNullTrackingCode() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        String productName = "TestName";
        String trackingCode = "";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;
        UUID userId = UUID.randomUUID();

        assertThrows(TrackingCodeException.class, () -> new Tracking(id, createdAt, updatedAt, productName, new TrackingCode(trackingCode), transporter, status, userId));
    }

    @Test
    public void newTrackingInstanceWithInvalidTrackingCode() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        String productName = "TestName";
        String trackingCode = "INVALIDCODE";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;
        UUID userId = UUID.randomUUID();

        assertThrows(TrackingCodeException.class, () -> new Tracking(id, createdAt, updatedAt, productName, new TrackingCode(trackingCode), transporter, status, userId));
    }
}
