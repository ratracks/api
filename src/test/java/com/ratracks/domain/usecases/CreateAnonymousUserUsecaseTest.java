package com.ratracks.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ratracks.domain.entities.AnonymousUser;
import com.ratracks.repositories.InMemoryUserRepository;

@ExtendWith(MockitoExtension.class)
public class CreateAnonymousUserUsecaseTest {

    private CreateAnonymousUserUsecase useCase;
    private InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        useCase = new CreateAnonymousUserUsecase(repository);
    }

    @Test
    void returnsAnonymousUser() {
        AnonymousUser createdUser = useCase.execute(null).getCreatedUser();

        assertInstanceOf(AnonymousUser.class, createdUser);
    }

    @Test
    void createsAnonymousUser() {
        AnonymousUser createdUser = useCase.execute(null).getCreatedUser();

        assertEquals(createdUser.getId(), repository.users.get(0).getId());
    }

    @Test
    void createsUniqueAnonymousUsers() {
        Set<String> names = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            AnonymousUser createdUser = useCase.execute(null).getCreatedUser();
            String name = createdUser.getName();

            assertTrue(name.startsWith("Anonymous_"));
            assertFalse(names.contains(name));
            names.add(name);
        }
    }
}
