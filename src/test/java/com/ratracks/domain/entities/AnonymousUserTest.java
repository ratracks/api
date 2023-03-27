package com.ratracks.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnonymousUserTest {

    @Test
    public void newInstance() {
        String name = "TestName";

        AnonymousUser entity = new AnonymousUser(name);

        assertEquals(name, entity.getName());
    }

}
