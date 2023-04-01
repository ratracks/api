package com.ratracks.repositories;

import java.util.ArrayList;

import com.ratracks.domain.contracts.repositories.UserRepository;
import com.ratracks.domain.entities.User;

public class InMemoryUserRepository implements UserRepository {
    public ArrayList<User> users = new ArrayList<User>();

    @Override
    public void create(User user) {
        users.add(user);
    }
}
