package com.beyond.util.character;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: yanbing
 * @Date: 2018/6/24 11:07
 */
public class StringLocalUtils {

    /**
     * 按字节截取字符串
     *
     * @param chstring
     * @param offset 字节偏移量
     * @param length 截取的字节数
     * @return
     */
    public static String subChString(String chstring, int offset, int length) {
        if (null == chstring || chstring.equals("") || chstring.length() == 0) {
            return chstring;
        }
        int num = 0;
        int index = -1;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chstring.length(); i++) {
            char c = chstring.charAt(i);
            int move = 0;
            if (isChinese(c)) {
                move = 2;
            } else {
                move = 1;
            }
            index += move;
            if (index >= offset) {
                sb.append(c);
                num += move;
            }
            if (num >= length) {
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符是不是中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        int ascii = (int) c;
        if (ascii >= 0 && ascii <= 255)
            return false;
        return true;
    }

    /**
     * unicode转中文
     *
     * @param str:待转换字符串
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
