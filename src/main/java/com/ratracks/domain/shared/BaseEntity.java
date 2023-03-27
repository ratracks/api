package com.ratracks.domain.shared;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseEntity {
    private String id;
    private Date createdAt;
    private Date updatedAt;
}