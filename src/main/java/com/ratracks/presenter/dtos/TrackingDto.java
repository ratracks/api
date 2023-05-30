package com.ratracks.presenter.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingDto {
  String id;
  String createdAt;
  String updatedAt;
  String productName;
  String trackingCode;
  String transporter;
  String status;
  String userId;
}
