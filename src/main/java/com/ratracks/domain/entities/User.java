package com.ratracks.domain.entities;

import com.ratracks.domain.shared.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class User extends BaseEntity {
    public User(String name) {
        super();

        this.name = name;
    }

    private String name;
}
