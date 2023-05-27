package com.ratracks.domain.entities.anonymoususer;

import com.ratracks.domain.entities.user.User;
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
