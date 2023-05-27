package com.ratracks.domain.usecases;

import java.util.UUID;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.factories.TrackingFactory;
import com.ratracks.domain.entities.tracking.Tracking;
import com.ratracks.domain.entities.tracking.valueobjects.TrackingCode;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
public class CreateTrackingUseCase implements UseCase<CreateTrackingUseCase.Input, CreateTrackingUseCase.Output> {

    @Value
    public static class Input {
        String productName;
        String trackingCode;
        Transporter transporter;
        Status status;
        UUID userId;
    }

    @Value
    public static class Output {
        Tracking createdTracking;
    }

    private final TrackingRepository repository;

    @Override
    public Output execute(Input input) {

        Tracking tracking = TrackingFactory.create(new TrackingFactory.Input(input.productName, input.trackingCode,
                input.transporter, input.status, input.userId));

        repository.create(tracking);

        return new CreateTrackingUseCase.Output(tracking);
    }
}
