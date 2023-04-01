package com.ratracks.domain.usecases;

import java.util.UUID;

import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.entities.AnonymousUser;

import lombok.Value;

public class CreateAnonymousUser implements UseCase<CreateAnonymousUser.Input, CreateAnonymousUser.Output> {

    public static class Input {}

    @Value
    public static class Output {
        private AnonymousUser createdUser;
    }

    private UserRepository repository;

    public CreateAnonymousUser(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Output execute(Input input) {
        String name = "Anonymous_" + UUID.randomUUID().toString();
        AnonymousUser user = new AnonymousUser(name);

        repository.create(user);

        return new CreateAnonymousUser.Output(user);
    }
}
