package com.ratracks.domain.contracts.services;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TrackingDetails {
    String trackCode;
    String type;
    String descriptionType;
    Date expectedDate;
    List<TrackingEvent> events;
}

