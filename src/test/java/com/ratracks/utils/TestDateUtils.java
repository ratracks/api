package com.ratracks.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");

    public static boolean assertEquals(Date firstDate, Date secondDate) {
        return formatter.format(firstDate).equals(formatter.format(secondDate));
    }
}
