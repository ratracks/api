package com.ratracks.utils.localDateTime;

import java.time.LocalDateTime;

public class ValidateTime {
    public static boolean isGreaterThanThirtyMinutes(LocalDateTime dateTimeToken) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenThirtyMinutesLater = dateTimeToken.plusMinutes(30);

        return now.isAfter(tokenThirtyMinutesLater);
    }
}
