package com.beyond.util.jdk8;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * JDK1.8所有和日期时间相关的新API
 */
public class NewDateTimeDemo {

    /**
     * ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
     */
    public static void exeZonedDateTime() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }

    /**
     * 每个时区都对应着 ID，地区ID都为 “{区域}/{城市}”的格式。可通过ZoneId获取
     * 例如 ：Asia/Shanghai 等
     * <p>
     * ZoneId：该类中包含了所有的时区信息
     * getAvailableZoneIds() : 可以获取所有时区信息 ,即of()方法需要的id值
     * of(id) : 用指定的时区信息获取 ZoneId 对象
     */
    public static void exeZoneId() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }


    /**
     * DateTimeFormatter代替SimpleDateFormat
     * DateTimeFormatter : 解析和格式化日期或时间
     * java.time.format.DateTimeFormatter 类：该类提供了三种格式化方法：
     *  预定义的标准格式
     *  语言环境相关的格式
     *  自定义的格式
     */
    public static void exeDateTimeFormatter() {
        //使用默认格式
        DateTimeFormatter dtfDefault = DateTimeFormatter.ISO_LOCAL_DATE;

        //自定义格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        LocalDateTime ldt = LocalDateTime.now();
        //两种格式化方式
        String strDate = ldt.format(dtf);
        System.out.println(dtf.format(ldt));
        System.out.println(strDate);

        //两种解析方式
        LocalDateTime newLdt = LocalDateTime.parse(strDate, dtf);
        System.out.println(dtf.parse(strDate));
        System.out.println(newLdt);
    }

    /**
     * TemporalAdjuster : 时间校正器
     * <p>
     * TemporalAdjuster : 时间校正器。有时我们可能需要获取例如：将日期调整到“下个周日”等操作。
     * TemporalAdjusters : 该类通过静态方法提供了大量的常用 TemporalAdjuster 的实现。
     */
    public static void exeTemporalAdjuster() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);

    }

    /**
     * Duration : 用于计算两个“时间”间隔
     * Period : 用于计算两个“日期”间隔
     */
    public static void exeDurationAndPeriod() {
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Instant ins2 = Instant.now();

        System.out.println("所耗费时间为：" + Duration.between(ins1, ins2));
        Duration duration = Duration.between(ins1, ins2);
        //获取这段时间的纳秒差值
        System.out.println("getNano : " + duration.getNano());
        //获取这段时间的秒差值
        System.out.println("getSeconds : " + duration.getSeconds());
        //获取这段时间的天差值
        System.out.println("toDays : " + duration.toDays());
        //获取这段时间的小时差值
        System.out.println("toHours : " + duration.toHours());
        //获取这段时间的毫秒差值
        System.out.println("toMillis : " + duration.toMillis());
        //获取这段时间的分钟差值
        System.out.println("toMinutes : " + duration.toMinutes());
        //获取这段时间的纳秒差值
        System.out.println("toNanos : " + duration.toNanos());

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);

        Period pe = Period.between(ld2, ld1);
        System.out.println("getYears : " + pe.getYears());
        System.out.println("getMonths : " + pe.getMonths());
        System.out.println("getDays : " + pe.getDays());
    }

    /**
     * 使用Instant代替Date
     * 用于“时间戳”的运算。它是以Unix元年(传统的设定为UTC时区1970年1月1日午夜时分)开始所经历的描述进行运算
     */
    public static void exeInstant() {
        Instant ins = Instant.now();  //默认使用 UTC(世界协调时间) 时区，比中国慢8小时
        System.out.println(ins);
        //the time-zone offset in hours, from -18 to +18
        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
//		OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(-8));
        System.out.println(odt);

        //Instant.ofEpochMilli()，指定从1970-01-01T00:00:00Z后的多少毫秒
        System.out.println("" + ins.toEpochMilli());

        //Instant.ofEpochMilli()，指定从1970-01-01T00:00:00Z后的多少秒
        System.out.println("" + ins.getEpochSecond());

        //指定一个值，计算从1970-01-01T00:00:00Z开始后的多少纳秒
        System.out.println(ins.getNano());

        //指定一个值，计算从1970-01-01T00:00:00Z开始后的多少秒
        Instant ins2 = Instant.ofEpochSecond(5);
        System.out.println(ins2);
    }


    /**
     * LocalDate、LocalTime、LocalDateTime 类的实例是不可变的对象(即线程安全)，
     * 分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。它们提供了简单的日期或时间，并不包含当前的时间信息。
     * 也不包含与时区相关的信息。
     * <p>
     * 注：ISO-8601日历系统是国际标准化组织制定的现代公民的日期和时间的表示法
     * <p>
     * isBefore, isAfter 比较两个 LocalDate
     * isLeapYear 判断是否是闰年
     * until 获得两个日期之间的 Period 对象，或者指定 ChronoUnits 的数字
     * plus, minus 添加或减少一个 Duration 或 Period
     * <p>
     * withDayOfMonth,withDayOfYear,withMonth,withYear  将月份天数、年份天数、月份、年份修改为指定的值并返回新的LocalDate对象
     */
    public static void exeLocalDateTime() {
        //静态方法，根据当前时间创建对象
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //获取指定日期时间
        //静态方法，根据指定日期/时间创建对象
        LocalDateTime ld2 = LocalDateTime.of(2016, 11, 21, 10, 10, 10);
        System.out.println(ld2);

        //LocalDateTime代替Calendar，进行时间的加减运算
        //日期时间加减运算
        //加运算：plusDays, plusWeeks,plusMonths, plusYears
        LocalDateTime ldt3 = ld2.plusYears(20);
        System.out.println(ldt3);
        LocalDateTime ldt31 = ld2.plus(20, ChronoUnit.YEARS);
        System.out.println(ldt31);
        //减运算：minusDays, minusWeeks,minusMonths, minusYears
        LocalDateTime ldt4 = ld2.minusMonths(2);
        System.out.println(ldt4);

        // 通过with修改某些值，修改年为2019
        LocalDateTime localDateTime1 = ld2.withYear(2019);
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2 = ld2.with(ChronoField.YEAR, 2019);
        System.out.println(localDateTime2);

        //firstDayOfMonth():设置为当月的第一天
        LocalDateTime localDateTime3 = ld2.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(localDateTime3);
        //lastDayOfMonth():设置为当月的最后一天
        LocalDateTime localDateTime4 = ld2.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(localDateTime4);

        //getDayOfMonth 获得月份天数(1-31)
        //getDayOfYear 获得年份天数(1-366)
        //getDayOfWeek 获得星期几(返回一个 DayOfWeek枚举值)
        //getMonth 获得月份, 返回一个 Month 枚举值
        //getMonthValue 获得月份(1-12)
        //getYear 获得年份
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
        //getYear 获得年份
        System.out.println(ldt.get(ChronoField.YEAR));
        //getDayOfMonth 获得月份天数(1-31)
        System.out.println(ldt.get(ChronoField.MONTH_OF_YEAR));
    }

}
