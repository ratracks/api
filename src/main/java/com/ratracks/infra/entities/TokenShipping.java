package com.ratracks.infra.entities;

import com.ratracks.domain.shared.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TokenShipping extends BaseEntity {

    public TokenShipping(String name, String token) {
        super();
        this.name = name;
        this.token = token;
    }

    private String name;
    private String token;
}
