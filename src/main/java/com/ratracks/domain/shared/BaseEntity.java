package com.ratracks.domain.shared;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
