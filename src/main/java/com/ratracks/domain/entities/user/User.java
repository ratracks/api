package com.ratracks.domain.entities.user;

import com.ratracks.domain.shared.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    public User(String name) {
        super();

        this.name = name;
    }

    private String name;
}
