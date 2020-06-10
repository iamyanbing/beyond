package com.beyond.util.character;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: yanbing
 * @Date: 2018/6/24 11:07
 */
public class StringLocalUtils {
    /**
     * unicode转中文
     *
     * @param str:待转换字符串
     *
     * @return
     */
    public static String decodeUnicode(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    public static String getStirngValueNull() {
        return String.valueOf(null);
    }

}
