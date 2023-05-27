package com.ratracks.domain.entities.factories;

import java.util.UUID;

import com.ratracks.domain.entities.tracking.Tracking;
import com.ratracks.domain.entities.tracking.valueobjects.TrackingCode;
import com.ratracks.domain.enums.Status;
import com.ratracks.domain.enums.Transporter;

import lombok.Value;

public class TrackingFactory {

  private TrackingFactory() {
  }

  @Value
  public static class Input {
    String productName;
    String trackingCode;
    Transporter transporter;
    Status status;
    UUID userId;
  }

  public static Tracking create(TrackingFactory.Input input) {
    return new Tracking(input.productName, new TrackingCode(input.trackingCode), input.transporter, input.status,
        input.userId);
  }
}
