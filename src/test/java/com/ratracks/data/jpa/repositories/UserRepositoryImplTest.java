package com.ratracks.data.jpa.repositories;

import com.ratracks.data.schemas.UserSchema;
import com.ratracks.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserRepositoryImplTest {

    @Mock
    private JpaUserRepository jpaUserRepository;

    private UserRepositoryImpl userRepository;

    public UserRepositoryImplTest() {
        MockitoAnnotations.openMocks(this);
        userRepository = new UserRepositoryImpl(jpaUserRepository);
    }

    @Test
    public void createUsers() {
        String name1 = "Foo";
        String name2 = "Bar";
        User user1 = new User(name1);
        User user2 = new User(name2);

        when(jpaUserRepository.save(any(UserSchema.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            UserSchema userSchema = (UserSchema) args[0];
            return new UserSchema(userSchema.getName());
        });

        userRepository.create(user1);
        userRepository.create(user2);

        ArgumentCaptor<UserSchema> userSchemaCaptor = ArgumentCaptor.forClass(UserSchema.class);
        verify(jpaUserRepository, times(2)).save(userSchemaCaptor.capture());

        assertEquals(name1, userSchemaCaptor.getAllValues().get(0).getName());
        assertEquals(name2, userSchemaCaptor.getAllValues().get(1).getName());
    }
}
