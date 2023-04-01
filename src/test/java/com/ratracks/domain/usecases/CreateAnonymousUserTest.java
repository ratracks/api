package com.ratracks.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ratracks.domain.entities.AnonymousUser;
import com.ratracks.repositories.InMemoryUserRepository;

@ExtendWith(MockitoExtension.class)
public class CreateAnonymousUserTest {

    private CreateAnonymousUser useCase;
    private InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        useCase = new CreateAnonymousUser(repository);
    }

    @Test
    void returnsAnonymousUser() {
        CreateAnonymousUser.Input input = new CreateAnonymousUser.Input();

        AnonymousUser createdUser = useCase.execute(input).getCreatedUser();

        assertInstanceOf(AnonymousUser.class, createdUser);
    }

    @Test
    void createsAnonymousUser() {
        CreateAnonymousUser.Input input = new CreateAnonymousUser.Input();

        AnonymousUser createdUser = useCase.execute(input).getCreatedUser();

        assertEquals(createdUser.getId(), repository.users.get(0).getId());
    }
}
