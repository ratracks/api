package com.ratracks.data.jpa.repositories;

import org.springframework.stereotype.Repository;

import com.ratracks.data.schemas.UserSchema;
import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.entities.User;
import com.ratracks.exceptions.CreateUserException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JpaUserRepository repository;

    public UserRepositoryImpl(JpaUserRepository repository){
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        try {
            UserSchema userSchema = new UserSchema(user.getName());
            repository.save(userSchema);
        } catch (Exception e) {
            throw new CreateUserException("Error creating user", e);
        }
    }
}
