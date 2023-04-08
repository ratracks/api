package com.ratracks.presenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.usecases.CreateAnonymousUserUsecase;

@Configuration
public class Module {

    @Bean
    public CreateAnonymousUserUsecase createAnonymousUserUsecase(UserRepository repository) {
        return new CreateAnonymousUserUsecase(repository);
    }

}
