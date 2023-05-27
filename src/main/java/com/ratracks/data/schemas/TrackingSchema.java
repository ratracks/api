package com.ratracks.data.schemas;

import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "trackings")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class TrackingSchema extends BaseEntitySchema {

    public TrackingSchema(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String productName,
                          String trackingCode, Transporter transporter, Status status, UUID userId) {
        super(id, createdAt, updatedAt);

        this.productName = productName;
        this.trackingCode = trackingCode;
        this.transporter = transporter;
        this.status = status;
        this.userId = userId;
    }

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String trackingCode;

    @Column(nullable = false)
    private Transporter transporter;

    @Column(nullable = false)
    private Status status;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private UserSchema user;
}