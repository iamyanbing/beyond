package com.beyond.util.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 捕获组
 *
 * @author huangyanbing
 * @date 2019-07-17 10:14
 */
public class CaptureGroup {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureGroup.class);

    /**
     * 普通捕获组
     * 从正则表达式左侧开始，每出现一个左括号"("记做一个分组，分组编号从 1 开始。0 代表整个表达式。
     *
     * @throws Exception
     */
    public static void exeGeneral() throws Exception {
        String DATE_STRING = "2019-07-17";
        String P_COMM = "(\\d{4})-((\\d{2})-(\\d{2}))";
        Pattern pattern = Pattern.compile(P_COMM);
        Matcher matcher = pattern.matcher(DATE_STRING);
        matcher.find();//必须要有这句
        LOGGER.info("matcher.group()  value:{}", matcher.group());
        LOGGER.info("matcher.group(0) value:{}", matcher.group(0));
        LOGGER.info("matcher.group(1) value:{}", matcher.group(1));
        LOGGER.info("matcher.group(2) value:{}", matcher.group(2));
        LOGGER.info("matcher.group(3) value:{}", matcher.group(3));
        LOGGER.info("matcher.group(4) value:{}", matcher.group(4));
    }

    /**
     * 命名捕获组
     * 每个以左括号开始的捕获组，都紧跟着 ?，而后才是正则表达式。
     *
     * @throws Exception
     */
    public static void exeName() throws Exception {
        String P_NAMED = "(?<year>\\d{4})-(?<md>(?<month>\\d{2})-(?<date>\\d{2}))";
        String DATE_STRING = "2019-07-17";

        Pattern pattern = Pattern.compile(P_NAMED);
        Matcher matcher = pattern.matcher(DATE_STRING);
        matcher.find();
        LOGGER.info("===========使用名称获取=============");
        LOGGER.info("matcher.group(0) value:{}", matcher.group(0));
        LOGGER.info("matcher.group('year') value:{}", matcher.group("year"));
        LOGGER.info("matcher.group('md') value:{}", matcher.group("md"));
        LOGGER.info("matcher.group('month') value:{}", matcher.group("month"));
        LOGGER.info("matcher.group('date') value:{}", matcher.group("date"));
        matcher.reset();
        LOGGER.info("===========使用编号获取=============");
        matcher.find();
        LOGGER.info("matcher.group(0) value:{}", matcher.group(0));
        LOGGER.info("matcher.group(1) value:{}", matcher.group(1));
        LOGGER.info("matcher.group(2) value:{}", matcher.group(2));
        LOGGER.info("matcher.group(3) value:{}", matcher.group(3));
        LOGGER.info("matcher.group(4) value:{}", matcher.group(4));
    }

    /**
     * 非捕获组
     * 在左括号后紧跟 ?:，而后再加上正则表达式，构成非捕获组 (?:Expression)。
     *
     * @throws Exception
     */
    public static void exeNon() throws Exception {
        String P_UNCAP = "(?:\\d{4})-((\\d{2})-(\\d{2}))";
        String DATE_STRING = "2019-07-17";

        Pattern pattern = Pattern.compile(P_UNCAP);
        Matcher matcher = pattern.matcher(DATE_STRING);
        matcher.find();
        LOGGER.info("matcher.group(0) value:{}", matcher.group(0));
        LOGGER.info("matcher.group(1) value:{}", matcher.group(1));
        LOGGER.info("matcher.group(2) value:{}", matcher.group(2));
        LOGGER.info("matcher.group(3) value:{}", matcher.group(3));

// Exception in thread "main" java.lang.IndexOutOfBoundsException: No group 4
        LOGGER.info("matcher.group(4) value:{}", matcher.group(4));
    }

}
