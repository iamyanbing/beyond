package com.beyond.util.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Auther: yanbing
 * @Date: 2018/6/24 11:07
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 根据当前系统时区把指定时间转换为UTC时间
     *
     * @param date:需要转换的时间
     *
     * @return
     */
    public static Long convertCurrentSystemZoneDateToUTC(Date date) {
        Calendar cal = Calendar.getInstance();
        //date为mull，则默认为当前系统时间
        if (date != null) {
            cal.setTime(date);
        }

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal.getTimeInMillis();
    }

    /**
     * 把UTC时间转换为当前系统时区时间
     *
     * @return
     *
     * @throws ParseException
     */
    public static Date getCurrentSystmZoneDateByUTC(String utc) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(utc);
    }

    /**
     * @param pattern
     * @param date
     *
     * @return
     *
     * @throws ParseException
     */
    public static Date getFormatDateByString(String pattern, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    public static void printSystemTimeZone() {
        Calendar calendar = Calendar.getInstance();
        LOGGER.info("系统当前时间：" + calendar.getTime());
        LOGGER.info("calendar.getTimeZone().getID()：：" + calendar.getTimeZone().getID());
        LOGGER.info("user.timezone：" + System.getProperty("user.timezone"));
        LOGGER.info("user.country：" + System.getProperty("user.country"));
        LOGGER.info("TimeZone.getDefault().getID()：" + TimeZone.getDefault().getID());
    }
}
