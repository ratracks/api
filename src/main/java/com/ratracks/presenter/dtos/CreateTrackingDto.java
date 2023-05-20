package com.ratracks.presenter.dtos;

import lombok.Data;

@Data
public class CreateTrackingDto {
  String productName;
  String trackingCode;
  String userId;
}
