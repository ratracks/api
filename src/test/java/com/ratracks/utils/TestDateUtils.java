package com.ratracks.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TestDateUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");

    public static boolean assertEquals(LocalDateTime firstDate, LocalDateTime secondDate) {
        Date firstDateAsDate = Date.from(firstDate.atZone(ZoneId.systemDefault()).toInstant());
        Date secondDateAsDate = Date.from(secondDate.atZone(ZoneId.systemDefault()).toInstant());
        
        return formatter.format(firstDateAsDate).equals(formatter.format(secondDateAsDate));
    }
}
