package com.ratracks.presenter.dtos;

import com.ratracks.domain.contracts.services.TrackingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class TrackingDto {
  String id;
  String createdAt;
  String updatedAt;
  String productName;
  String trackingCode;
  String transporter;
  String status;
  String userId;
  TrackingDetails trackingDetails;

  public TrackingDto(String id, String createdAt, String updatedAt, String productName, String trackingCode,
                     String transporter, String status, String userId) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.productName = productName;
    this.trackingCode = trackingCode;
    this.transporter = transporter;
    this.status = status;
    this.userId = userId;
  }

}
