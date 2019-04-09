package com.example.jinny.expensesnote.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getTimeFromLong(long time) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        return format.format(new Date(time));
    }

    public static boolean isEmpty(String s) {
        if (s.length() == 0) return true;
        return false;
    }
}
