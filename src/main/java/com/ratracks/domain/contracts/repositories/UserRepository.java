package com.ratracks.domain.contracts.repositories;

import com.ratracks.domain.entities.User;

public interface UserRepository {
    void create(User user);
}
