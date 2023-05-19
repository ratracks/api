package com.ratracks.domain.usecases;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class CreateTrackingUseCase implements UseCase<CreateTrackingUseCase.Input, CreateTrackingUseCase.Output> {

    @Value
    public static class Input {
        String productName;
        String trackingCode;
        Transporter transporter;
        Status status;
    }

    @Value
    public static class Output {
        Tracking createTracking;
    }

    private final TrackingRepository repository;

    @Override
    public Output execute(Input input) {

        Tracking tracking = new Tracking(null, null, null, input.productName, input.trackingCode, input.transporter, input.status);

        repository.create(tracking);

        return new CreateTrackingUseCase.Output(tracking);
    }
}
