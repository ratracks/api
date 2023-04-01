package com.ratracks.domain.usecases;

import java.util.UUID;

import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.entities.AnonymousUser;

import lombok.Value;

public class CreateAnonymousUser implements UseCase<Void, CreateAnonymousUser.Output> {

    @Value
    public static class Output {
        private AnonymousUser createdUser;
    }

    private UserRepository repository;

    public CreateAnonymousUser(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Output execute(Void input) {
        String randomUUID = UUID.randomUUID().toString();
        String name = "Anonymous_" + randomUUID.substring(0, 8);
        AnonymousUser user = new AnonymousUser(name);

        repository.create(user);

        return new CreateAnonymousUser.Output(user);
    }
}
