package com.ratracks.data.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"name"})
public class UserSchema extends BaseEntitySchema {
    
    @Column(nullable = false)
    private String name;
    
}
