package com.ratracks.domain.shared;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class BaseEntity {

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    private UUID id;
    private Date createdAt;
    private Date updatedAt;
}
