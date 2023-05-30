package com.ratracks.utils.localDateTime;

import java.time.LocalDateTime;

public class ValidateTime {
    public static boolean isGreaterThanThirtyMinutes(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyMinutesLater = now.plusMinutes(30);

        return dateTime.isAfter(thirtyMinutesLater);
    }
}
