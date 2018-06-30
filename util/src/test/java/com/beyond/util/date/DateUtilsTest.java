package com.beyond.util.date;

import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class DateUtilsTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    @Ignore
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Ignore
    @Test
    public void getUTCByCurrentSystemDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long utc = DateUtils.convertCurrentSystemZoneDateToUTC(simpleDateFormat.parse("2018-06-24 12:00:00"));
        System.out.println(new Date(utc));
        System.out.println(utc);

        long utcNull = DateUtils.convertCurrentSystemZoneDateToUTC(null);
        System.out.println(new Date(utcNull));
        System.out.println(utcNull);
    }

    @Ignore
    @Test
    public void getUTConvertToCurrentSystemZoneDate() throws ParseException {
        System.out.println(DateUtils.getCurrentSystmZoneDateByUTC("2018/05/08 12:00:00"));
    }

    /**
     * 西八区转东八区
     *
     * @throws ParseException
     */
    @Ignore
    @Test
    public void getFormatDateByString() throws ParseException {
        System.out.println(DateUtils.getFormatDateByString("yyyy-MM-dd'T'HH:mm:ssXXX", "2018-01-06T01:23:06-08:00"));
    }

    @Ignore
    @Test
    public void printSystemTimeZone() throws ParseException {
        DateUtils.printSystemTimeZone();
    }
}
