package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.tracking.Tracking;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor
public class GetTrackingDetailsUseCase implements UseCase<GetTrackingDetailsUseCase.Input, GetTrackingDetailsUseCase.Output>{

    @Value
    public static class Input {
        UUID trackingId;
    }

    @Value
    public static class Output {
        Tracking tracking;
    }

    private final TrackingRepository repository;

    @Override
    public Output execute(Input input) {
        Tracking tracking = repository.getById(input.getTrackingId());

        return new Output(tracking);
    }

}
