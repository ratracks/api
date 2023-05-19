package com.ratracks.data.jpa.repositories;

import com.ratracks.data.schemas.TrackingSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTrackingRepository extends JpaRepository<TrackingSchema, UUID> {
}
