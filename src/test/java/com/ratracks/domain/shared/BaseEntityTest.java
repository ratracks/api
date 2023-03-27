package com.ratracks.domain.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ratracks.utils.TestDateUtils;

public class BaseEntityTest {
    
    @Test
    public void newInstance() {
        BaseEntity entity = new BaseEntity();

        assertEquals(UUID.class, entity.getId().getClass());
        assertTrue(TestDateUtils.assertEquals(entity.getCreatedAt(), new Date()));
        assertTrue(TestDateUtils.assertEquals(entity.getUpdatedAt(), new Date()));
    }
    
}