package com.ratracks.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void newInstance() {
        String name = "TestName";

        User entity = new User(name);

        assertEquals(name, entity.getName());
    }

}