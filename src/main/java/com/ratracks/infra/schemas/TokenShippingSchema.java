package com.ratracks.infra.schemas;

import com.ratracks.data.schemas.BaseEntitySchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class TokenShippingSchema extends BaseEntitySchema {


    public TokenShippingSchema (UUID id,
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt,
                                String name,
                                String token) {
        super(id, createdAt, updatedAt);

        this.name = name;
        this.token = token;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String token;
}
