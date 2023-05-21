package com.ratracks.domain.entities;

import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import com.ratracks.domain.shared.BaseEntity;

import com.ratracks.exceptions.TrackingCodeException;
import com.ratracks.utils.regex.TrackingRegex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
public class Tracking extends BaseEntity {
    public Tracking(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String productName, String trackingCode,
            Transporter transporter, Status status, UUID userId) {
        super(id, createdAt, updatedAt);
            
        this.initializeTracking(productName, trackingCode, transporter, status, userId);
    }

    public Tracking(String productName, String trackingCode,
            Transporter transporter, Status status, UUID userId) {
        super();

        this.initializeTracking(productName, trackingCode, transporter, status, userId);
    }

    public Tracking() {
        super();
    }

    private void initializeTracking(String productName, String trackingCode,
            Transporter transporter, Status status, UUID userId) {
        if (productName == null || productName.trim().isEmpty()) {
            this.productName = trackingCode;
        } else {
            this.productName = productName;
        }
        if (trackingCode == null || trackingCode.trim().isEmpty()) {
            throw new TrackingCodeException("Tracking code cannot be empty", new IllegalArgumentException());
        } else if (!trackingCode.matches(TrackingRegex.CORREIOS_VALIDATOR)) {
            throw new TrackingCodeException("Invalid tracking code", new IllegalArgumentException());
        }

        this.trackingCode = trackingCode;
        this.status = status != null ? status : Status.IN_PROGRESS;
        this.transporter = transporter != null ? transporter : Transporter.CORREIOS;
        this.userId = userId;
    }

    private String productName;
    private String trackingCode;
    private Transporter transporter;
    private Status status;
    private UUID userId;
}
