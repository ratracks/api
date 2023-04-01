package com.ratracks.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AnonymousUser extends User {

    public AnonymousUser(String name) {
        super(name);
    }

}
