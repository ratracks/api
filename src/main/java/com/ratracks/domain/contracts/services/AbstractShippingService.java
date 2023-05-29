package com.ratracks.domain.contracts.services;

public abstract class AbstractShippingService {
    public abstract String getDetailsByTrackingCode(String trackCode) throws Exception;
}
