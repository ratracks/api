package com.ratracks.domain.entities;

import java.util.Date;

import com.ratracks.domain.shared.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class User extends BaseEntity {
    public User(String name, String id, Date createdAt, Date updatedAt) {
        super(id, createdAt, updatedAt);

        this.name = name;
    }

    private String name;
}
