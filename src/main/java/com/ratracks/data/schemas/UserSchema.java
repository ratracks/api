package com.ratracks.data.schemas;

import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"name"})
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class UserSchema extends BaseEntitySchema {

    public UserSchema(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String name) {
        super(id, createdAt, updatedAt);

        this.name = name;
    }

    @Column(nullable = false)
    private String name;

}
