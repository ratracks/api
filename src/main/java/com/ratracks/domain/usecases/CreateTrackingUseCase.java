package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
public class CreateTrackingUseCase implements UseCase<CreateTrackingUseCase.Input, CreateTrackingUseCase.Output> {

    @Value
    public static class Input {
        String productName;
        String trackingCode;
    }

    @Value
    public static class Output {
        Tracking createTracking;
    }

    private final TrackingRepository repository;

    @Override
    public Output execute(Input input) {

        Tracking tracking = new Tracking(input.productName, input.trackingCode);

        repository.create(tracking);

        return new CreateTrackingUseCase.Output(tracking);
    }
}
