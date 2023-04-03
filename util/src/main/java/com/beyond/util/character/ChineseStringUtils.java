package com.beyond.util.character;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 缺点：只能检测出中文汉字不能检测中文标点.
 * 优点：利用正则效率高.
 *
 * 效率既高又能检测出中文汉字和中文标点 方式如下(简单来讲就是把所有中文标点都列出来：
 * Pattern pattern = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]")
 */
public class ChineseStringUtils {
    private static final String CHINESE_PATTERN = "[\u4e00-\u9fa5]";

    private static Pattern pattern;

    static {
        pattern = Pattern.compile(CHINESE_PATTERN);
    }

    /**
     * 判断字符串是否包含中文
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Matcher m = pattern.matcher(str);
        return m.find();
    }

}
