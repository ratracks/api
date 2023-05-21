package com.ratracks.data.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratracks.data.schemas.UserSchema;

public interface JpaUserRepository extends JpaRepository<UserSchema, UUID>{
    
}
