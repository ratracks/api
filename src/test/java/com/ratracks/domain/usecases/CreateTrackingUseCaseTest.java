package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        CreateTrackingUseCase.Input input = new CreateTrackingUseCase.Input("TestProduct", "AA123456789BR");

        Tracking result = createTrackingUseCase.execute(input).getCreateTracking();

        assertInstanceOf(Tracking.class, result);
    }

    @Test
    public void createTracking() {
        CreateTrackingUseCase.Input input = new CreateTrackingUseCase.Input("TestProduct", "AA123456789BR");

        createTrackingUseCase.execute(input);

        verify(repository, times(1)).create(any(Tracking.class));
    }

}
