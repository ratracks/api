package com.ratracks.domain.entities;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AnonymousUser extends User {

    public AnonymousUser(String name, String id, Date createdAt, Date updatedAt) {
        super(name, id, createdAt, updatedAt);
    }

}
