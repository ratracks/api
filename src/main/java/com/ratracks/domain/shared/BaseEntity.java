package com.ratracks.domain.shared;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class BaseEntity {

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
