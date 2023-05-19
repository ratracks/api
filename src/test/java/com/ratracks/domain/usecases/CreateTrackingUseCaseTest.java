package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;

import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

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

        CreateTrackingUseCase.Input input = new CreateTrackingUseCase.Input(productName, trackingCode, transporter, status);

        Tracking result = createTrackingUseCase.execute(input).getCreateTracking();

        assertInstanceOf(Tracking.class, result);
    }

    @Test
    public void createTracking() {

        String productName = "TestName";
        String trackingCode = "AA123456789BR";
        Status status = Status.IN_PROGRESS;
        Transporter transporter = Transporter.CORREIOS;

        CreateTrackingUseCase.Input input = new CreateTrackingUseCase.Input(productName, trackingCode, transporter, status);

        createTrackingUseCase.execute(input);

        verify(repository, times(1)).create(any(Tracking.class));
    }

}
