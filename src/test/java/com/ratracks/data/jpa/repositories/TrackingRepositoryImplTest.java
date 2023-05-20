package com.ratracks.data.jpa.repositories;
import com.ratracks.data.schemas.TrackingSchema;
import com.ratracks.domain.entities.Tracking;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import com.ratracks.exceptions.CreateTrackingException;
import com.ratracks.exceptions.GetAllTrackingsException;
import com.ratracks.exceptions.GetTrackingByIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackingRepositoryImplTest {

    @Mock
    private JpaTrackingRepository mockRepository;

    private TrackingRepositoryImpl trackingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trackingRepository = new TrackingRepositoryImpl(mockRepository);
    }

    @Test
    void createTracking() {
        // Arrange
        Tracking tracking = createDummyTracking();
        ArgumentCaptor<TrackingSchema> trackingSchemaCaptor = ArgumentCaptor.forClass(TrackingSchema.class);

        // Act
        trackingRepository.create(tracking);

        // Assert
        verify(mockRepository).save(trackingSchemaCaptor.capture());
        TrackingSchema capturedSchema = trackingSchemaCaptor.getValue();
        assertNotNull(capturedSchema);
        assertEquals(tracking.getCreatedAt(), capturedSchema.getCreatedAt());
        assertEquals(tracking.getUpdatedAt(), capturedSchema.getUpdatedAt());
        assertEquals(tracking.getProductName(), capturedSchema.getProductName());
        assertEquals(tracking.getTrackingCode(), capturedSchema.getTrackingCode());
        assertEquals(tracking.getTransporter(), capturedSchema.getTransporter());
        assertEquals(tracking.getStatus(), capturedSchema.getStatus());
    }

    @Test
    void createTrackingException() {
        // Arrange
        Tracking tracking = createDummyTracking();
        doThrow(RuntimeException.class).when(mockRepository).save(any(TrackingSchema.class));

        // Act & Assert
        assertThrows(CreateTrackingException.class, () -> trackingRepository.create(tracking));
    }

    @Test
    void deleteTracking() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        trackingRepository.delete(id);

        // Assert
        verify(mockRepository).deleteById(id);
    }

    @Test
    void getById() {
        // Arrange
        UUID id = UUID.randomUUID();
        TrackingSchema trackingSchema = createDummyTrackingSchema(id,  Optional.empty());
        when(mockRepository.findById(id)).thenReturn(Optional.of(trackingSchema));

        // Act
        Tracking result = trackingRepository.getById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(trackingSchema.getCreatedAt(), result.getCreatedAt());
        assertEquals(trackingSchema.getUpdatedAt(), result.getUpdatedAt());
        assertEquals(trackingSchema.getProductName(), result.getProductName());
        assertEquals(trackingSchema.getTrackingCode(), result.getTrackingCode());
        assertEquals(trackingSchema.getTransporter(), result.getTransporter());
        assertEquals(trackingSchema.getStatus(), result.getStatus());
    }

    @Test
    void getByIdException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(mockRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GetTrackingByIdException.class, () -> trackingRepository.getById(id));
    }

    @Test
    void listAllTrackings() throws GetAllTrackingsException {
        // Arrange
        List<TrackingSchema> trackingSchemas = createDummyTrackingSchemas();
        when(mockRepository.findAll()).thenReturn(trackingSchemas);

        // Act
        List<Tracking> result = trackingRepository.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(trackingSchemas.size(), result.size());

        for (int i = 0; i < trackingSchemas.size(); i++) {
            TrackingSchema schema = trackingSchemas.get(i);
            Tracking tracking = result.get(i);

            assertEquals(schema.getId(), tracking.getId());
            assertEquals(schema.getCreatedAt(), tracking.getCreatedAt());
            assertEquals(schema.getUpdatedAt(), tracking.getUpdatedAt());
            assertEquals(schema.getProductName(), tracking.getProductName());
            assertEquals(schema.getTrackingCode(), tracking.getTrackingCode());
            assertEquals(schema.getTransporter(), tracking.getTransporter());
            assertEquals(schema.getStatus(), tracking.getStatus());
        }
    }

    @Test
    void listAllTrackingsByStatusAndUserId() throws GetAllTrackingsException {
        // Arrange
        List<TrackingSchema> trackingSchemas = createDummyTrackingSchemas();
        when(mockRepository.findByStatusAndUserId(any(Status.class), any(UUID.class))).thenReturn(trackingSchemas);

        // Act
        List<Tracking> result = trackingRepository.getAll(Status.IN_PROGRESS, UUID.randomUUID());

        // Assert
        assertNotNull(result);
        assertEquals(trackingSchemas.size(), result.size());

        for (int i = 0; i < trackingSchemas.size(); i++) {
            TrackingSchema schema = trackingSchemas.get(i);
            Tracking tracking = result.get(i);

            assertEquals(schema.getId(), tracking.getId());
            assertEquals(schema.getCreatedAt(), tracking.getCreatedAt());
            assertEquals(schema.getUpdatedAt(), tracking.getUpdatedAt());
            assertEquals(schema.getProductName(), tracking.getProductName());
            assertEquals(schema.getTrackingCode(), tracking.getTrackingCode());
            assertEquals(schema.getTransporter(), tracking.getTransporter());
            assertEquals(schema.getStatus(), tracking.getStatus());
        }
    }

    @Test
    void listAllTrackingsException() {
        // Arrange
        when(mockRepository.findAll()).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(GetAllTrackingsException.class, () -> trackingRepository.getAll());
    }

    // Helper methods to create dummy data for tests

    private Tracking createDummyTracking() {
        Tracking tracking = new Tracking();
        UUID id = UUID.randomUUID();
        tracking.setId(id);
        tracking.setCreatedAt(LocalDateTime.now());
        tracking.setUpdatedAt(LocalDateTime.now());
        tracking.setProductName("Test Product");
        tracking.setTrackingCode("AA123456789BR");
        tracking.setTransporter(Transporter.CORREIOS);
        tracking.setStatus(Status.IN_PROGRESS);
        return tracking;
    }

    private TrackingSchema createDummyTrackingSchema(UUID id, Optional<Status> status) {
        TrackingSchema trackingSchema = new TrackingSchema();
        trackingSchema.setId(id);
        trackingSchema.setCreatedAt(LocalDateTime.now());
        trackingSchema.setUpdatedAt(LocalDateTime.now());
        trackingSchema.setProductName("Test Product");
        trackingSchema.setTrackingCode("AA123456789BR");
        trackingSchema.setTransporter(Transporter.CORREIOS);
        trackingSchema.setStatus(status.isPresent() ? status.get() : Status.IN_PROGRESS);
        return trackingSchema;
    }

    private List<TrackingSchema> createDummyTrackingSchemas() {
        List<TrackingSchema> trackingSchemas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UUID id = UUID.randomUUID();
            trackingSchemas.add(createDummyTrackingSchema(id, Optional.empty()));
        }
        return trackingSchemas;
    }
}
