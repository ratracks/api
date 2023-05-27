package com.ratracks.domain.contracts.repositories;

import com.ratracks.domain.entities.tracking.Tracking;
import com.ratracks.domain.enums.Status;

import java.util.List;
import java.util.UUID;

public interface TrackingRepository {
    void create(Tracking tracking);
    void delete(UUID trackingId);
    Tracking getById(UUID trackingId);
    List<Tracking> getAll();
    List<Tracking> getAll(Status status, UUID userId);
}
