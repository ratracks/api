package com.ratracks.presenter;

import com.ratracks.domain.usecases.GetTrackingDetailsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.usecases.CreateAnonymousUserUsecase;
import com.ratracks.domain.usecases.CreateTrackingUseCase;
import com.ratracks.domain.usecases.GetTrackingsUseCase;

@Configuration
public class Module {

    @Bean
    public CreateAnonymousUserUsecase createAnonymousUserUsecase(UserRepository repository) {
        return new CreateAnonymousUserUsecase(repository);
    }

    @Bean
    public GetTrackingsUseCase getTrackingsUseCase(TrackingRepository repository) {
        return new GetTrackingsUseCase(repository);
    }

    @Bean
    public GetTrackingDetailsUseCase getTrackingDetailsUseCase(TrackingRepository repository) {
        return new GetTrackingDetailsUseCase(repository);
    }

    @Bean
    public CreateTrackingUseCase createTrackingUseCase(TrackingRepository repository) {
        return new CreateTrackingUseCase(repository);
    }

}
