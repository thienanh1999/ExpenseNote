package com.example.jinny.expensesnote.utils;

import com.example.jinny.expensesnote.R;

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

    public static int[] getListColor() {
        return new int[] {R.color.GreenYellow, R.color.greenButton, R.color.BlueViolet,
                R.color.Brown, R.color.Yellow, R.color.Beige,
                R.color.Bisque, R.color.Red, R.color.Cyan,
                R.color.Olive, R.color.Orange, R.color.Blue,
                R.color.Coral, R.color.Crimson, R.color.Purple};
    }
}
