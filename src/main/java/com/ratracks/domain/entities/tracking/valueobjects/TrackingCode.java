package com.ratracks.domain.entities.tracking.valueobjects;

import com.ratracks.exceptions.TrackingCodeException;
import com.ratracks.utils.regex.TrackingRegex;

public class TrackingCode {
    private String code;

    public TrackingCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new TrackingCodeException("Tracking code cannot be empty", new IllegalArgumentException());
        } else if (!code.matches(TrackingRegex.CORREIOS_VALIDATOR)) {
            throw new TrackingCodeException("Invalid tracking code", new IllegalArgumentException());
        }

        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isValid() {
        return code.matches(TrackingRegex.CORREIOS_VALIDATOR);
    }

    public boolean isEmpty() {
        return code.isEmpty();
    }

    @Override
    public String toString() {
        return code;
    }
}
