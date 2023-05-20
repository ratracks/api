package com.ratracks.data.jpa.repositories;

import com.ratracks.data.schemas.TrackingSchema;
import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;
import com.ratracks.domain.enums.Status;
import com.ratracks.exceptions.CreateTrackingException;
import com.ratracks.exceptions.GetAllTrackingsException;
import com.ratracks.exceptions.GetTrackingByIdException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TrackingRepositoryImpl implements TrackingRepository {

    private final JpaTrackingRepository repository;

    public TrackingRepositoryImpl(JpaTrackingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Tracking tracking) {
        try {
            TrackingSchema trackingSchema = new TrackingSchema(
                    tracking.getId(),
                    tracking.getCreatedAt(),
                    tracking.getUpdatedAt(),
                    tracking.getProductName(),
                    tracking.getTrackingCode(),
                    tracking.getTransporter(),
                    tracking.getStatus(),
                    tracking.getUserId());
            repository.save(trackingSchema);
        } catch (Throwable e) {
            throw new CreateTrackingException("Error creating tracking", e);
        }
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Tracking getById(UUID id) {
        Optional<TrackingSchema> optional = repository.findById(id);
        if (optional.isPresent()) {
            TrackingSchema trackingSchema = optional.get();
            return new Tracking(
                    trackingSchema.getId(),
                    trackingSchema.getCreatedAt(),
                    trackingSchema.getUpdatedAt(),
                    trackingSchema.getProductName(),
                    trackingSchema.getTrackingCode(),
                    trackingSchema.getTransporter(),
                    trackingSchema.getStatus(),
                    trackingSchema.getUserId());
        } else {
            throw new GetTrackingByIdException("Tracking not found for ID: " + id, null);
        }
    }

    @Override
    public List<Tracking> getAll() throws GetAllTrackingsException {
        try {
            List<TrackingSchema> trackingSchemas = repository.findAll();
            List<Tracking> trackings = new ArrayList<>();
            for (TrackingSchema trackingSchema : trackingSchemas) {
                trackings.add(new Tracking(
                        trackingSchema.getId(),
                        trackingSchema.getCreatedAt(),
                        trackingSchema.getUpdatedAt(),
                        trackingSchema.getProductName(),
                        trackingSchema.getTrackingCode(),
                        trackingSchema.getTransporter(),
                        trackingSchema.getStatus(),
                        trackingSchema.getUserId()));
            }
            return trackings;
        } catch (Exception e) {
            throw new GetAllTrackingsException("Error when listing all trackings", e);
        }
    }

        @Override
    public List<Tracking> getAll(Status status, UUID userId) throws GetAllTrackingsException {
        try {
            List<TrackingSchema> trackingSchemas = repository.findByStatusAndUserId(status, userId);

            List<Tracking> trackings = new ArrayList<>();
            for (TrackingSchema trackingSchema : trackingSchemas) {
                trackings.add(new Tracking(
                        trackingSchema.getId(),
                        trackingSchema.getCreatedAt(),
                        trackingSchema.getUpdatedAt(),
                        trackingSchema.getProductName(),
                        trackingSchema.getTrackingCode(),
                        trackingSchema.getTransporter(),
                        trackingSchema.getStatus(),
                        trackingSchema.getUserId()));
            }
            return trackings;
        } catch (Exception e) {
            throw new GetAllTrackingsException("Error when listing all trackings", e);
        }
    }
}
