package com.ratracks.domain.contracts.services;

public abstract class AbstractShippingService {
    public abstract TrackingDetails getDetailsByTrackingCode(String trackCode) throws Exception;
}
