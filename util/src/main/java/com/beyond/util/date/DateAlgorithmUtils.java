package com.beyond.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Auther: yanbing
 * @Date: 2018/6/26 15:08
 */
public class DateAlgorithmUtils {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getDateAddAndSubtract(String date, int day) throws Exception {
        SimpleDateFormat dayformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayformat.parse(date));
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return simpleDateFormat.format(calendar.getTime());
    }
}
