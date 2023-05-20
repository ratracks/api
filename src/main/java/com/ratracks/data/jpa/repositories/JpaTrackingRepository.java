package com.ratracks.data.jpa.repositories;

import com.ratracks.data.schemas.TrackingSchema;
import com.ratracks.domain.enums.Status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTrackingRepository extends JpaRepository<TrackingSchema, UUID> {
  List<TrackingSchema> findByStatusAndUserId(Status status, String userId);
}
