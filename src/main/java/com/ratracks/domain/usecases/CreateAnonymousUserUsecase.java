package com.ratracks.domain.usecases;

import java.util.UUID;

import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.entities.anonymoususer.AnonymousUser;

import lombok.Value;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAnonymousUserUsecase implements UseCase<Void, CreateAnonymousUserUsecase.Output> {

    @Value
    public static class Output {
        AnonymousUser createdUser;
    }

    private final UserRepository repository;

    @Override
    public Output execute(Void input) {
        String randomUUID = UUID.randomUUID().toString();
        String name = "Anonymous_" + randomUUID.substring(0, 8);
        AnonymousUser user = new AnonymousUser(name);

        repository.create(user);

        return new CreateAnonymousUserUsecase.Output(user);
    }
}
