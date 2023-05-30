package com.ratracks.domain.contracts.services;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TrackingEvent {
    Date eventDate;
    String description;
    String type;
    String city;
    String uf;
}
