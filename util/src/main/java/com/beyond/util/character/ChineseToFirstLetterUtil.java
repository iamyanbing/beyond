package com.beyond.util.character;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文 拼音 首字母 大写
 */
public class ChineseToFirstLetterUtil {

    public static void main(String[] args) {
        System.out.println(ChineseToFirstLetterUtil.getPingYin("黄岩兵"));
        System.out.println(ChineseToFirstLetterUtil.getPinYinHeadChar("黄岩兵"));
        System.out.println(ChineseToFirstLetterUtil.getPinYinHeadChar("jack"));
        System.out.println(ChineseToFirstLetterUtil.ChineseToFirstLetter("abc"));
        System.out.println(ChineseToFirstLetterUtil.ChineseToFirstLetter("黄岩兵"));
    }

    /**
     * 根据汉字获取全拼
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        //设置拼音大小写 LOWERCASE(小写)  UPPERCASE(大写)
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置声调
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 根据汉字获取首字母
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str){
        String convert="";
        for(int j=0;j<str.length();j++){
            char word=str.charAt(j);
            String[] pinyinArray= PinyinHelper.toHanyuPinyinStringArray(word);
            if(pinyinArray!=null){
                convert +=pinyinArray[0].charAt(0);
            }else{
                convert+=word;
            }
        }
        return convert;
    }

    /**
     * 根据汉字获取首字母
     * @param c
     * @return
     */
    public static String ChineseToFirstLetter(String c) {
        StringBuilder string = new StringBuilder();
        char b;
        for (int k = 0; k < c.length(); k++) {
            b = c.charAt(k);
            String d = String.valueOf(b);
            String str = converterToFirstSpell(d);
            String s = str.toUpperCase();
            char h;
            for (int y = 0; y <= 0; y++) {
                h = s.charAt(0);
                string.append(h);
            }
        }
        return string.toString();
    }

    private static String converterToFirstSpell(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : nameChar) {
            String s = String.valueOf(c);
            if (s.matches("[\\u4e00-\\u9fa5]")) {
                try {
                    String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                    pinyinName.append(mPinyinArray[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(c);
            }
        }
        return pinyinName.toString();
    }

}
