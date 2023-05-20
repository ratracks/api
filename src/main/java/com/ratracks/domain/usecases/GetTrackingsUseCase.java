package com.ratracks.domain.usecases;

import java.util.List;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.entities.Tracking;
import com.ratracks.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
public class GetTrackingsUseCase implements UseCase<GetTrackingsUseCase.Input, GetTrackingsUseCase.Output> {

    @Value
    public static class Input {
        String userId;
        Status status;
    }

    @Value
    public static class Output {
        List<Tracking> trackings;
    }

    private final TrackingRepository repository;

    @Override
    public Output execute(Input input) {
        List<Tracking> trackings = repository.getAll(input.getStatus(), input.getUserId());

        return new GetTrackingsUseCase.Output(trackings);
    }
}
