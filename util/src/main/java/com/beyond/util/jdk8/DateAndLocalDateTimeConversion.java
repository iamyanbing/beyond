package com.beyond.util.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.util.Date;

/**
 * @author huangyanbing
 * @date 2019-09-16 20:08
 */
public class DateAndLocalDateTimeConversion {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateAndLocalDateTimeConversion.class);

    /**
     * 将java.util.Date转换为ZonedDateTime。
     * 使用它的toLocalDateTime()(也有toLocalDate())方法从ZonedDateTime获取LocalDateTime（转成LocalDate）。
     */
    private static void convertDateToLocalDateTime() {
        //从instant获取ZonedDateTime并使用其方法toLocalDateTime（）获取LocalDateTime
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
//        LocalDate localDate = zonedDateTime.toLocalDate();
        LOGGER.info("Date = " + date);
        LOGGER.info("LocalDateTime = " + localDateTime);

        //使用LocalDateTime的ofInstant()
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        LOGGER.info("localDateTime1 = " + localDateTime1);
    }

    /**
     * 使用atZone（）方法将LocalDateTime转换为ZonedDateTime
     * 将ZonedDateTime转换为Instant，并从中获取Date
     */
    private static void convertLocalDateTimeToDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

        //LocalDate 转 ZonedDateTime 方法不一样，用atStartOfDay()
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        Date date = Date.from(zonedDateTime.toInstant());

        LOGGER.info("LocalDateTime = " + localDateTime);
        LOGGER.info("Date = " + date);
    }
}
