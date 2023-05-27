package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.tracking.Tracking;

import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

import java.util.UUID;

public class CreateTrackingUseCaseTest {

    @Mock
    private TrackingRepository repository;

    private CreateTrackingUseCase createTrackingUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createTrackingUseCase = new CreateTrackingUseCase(repository);
    }

    @Test
    public void returnsTracking () {
        String productName = "TestName";
        String trackingCode = "AA123456789BR";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;
        UUID userId = UUID.randomUUID();

        CreateTrackingUseCase.Input input = new CreateTrackingUseCase.Input(productName, trackingCode, transporter, status, userId);

        Tracking result = createTrackingUseCase.execute(input).getCreatedTracking();

        assertInstanceOf(Tracking.class, result);
    }

    @Test
    public void createTracking() {
        String productName = "TestName";
        String trackingCode = "AA123456789BR";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;
        UUID userId = UUID.randomUUID();

        CreateTrackingUseCase.Input input = new CreateTrackingUseCase.Input(productName, trackingCode, transporter, status, userId);

        createTrackingUseCase.execute(input);

        verify(repository, times(1)).create(any(Tracking.class));
    }

}
