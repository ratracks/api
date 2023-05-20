package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;

import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GetTrackingUseCaseTest {

    @Mock
    private TrackingRepository repository;

    private GetTrackingsUseCase getTrackingsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getTrackingsUseCase = new GetTrackingsUseCase(repository);
    }

    @Test
    void returnsTrackings() {
        String userId = UUID.randomUUID().toString();
        Status status = Status.IN_PROGRESS;

        List<Tracking> mockEntities = Arrays.asList(this.createDummyTracking());

        when(repository.getAll(status, userId)).thenReturn(mockEntities);

        GetTrackingsUseCase.Input input = new GetTrackingsUseCase.Input(userId, status);

        List<Tracking> result = getTrackingsUseCase.execute(input).getTrackings();

        assertInstanceOf(List.class, result);
    }

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
}
