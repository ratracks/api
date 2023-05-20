package com.ratracks.presenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ratracks.domain.contracts.repositories.TrackingRepository;
import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.usecases.CreateAnonymousUserUsecase;
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

}
