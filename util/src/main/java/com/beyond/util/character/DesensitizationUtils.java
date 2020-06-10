package com.beyond.util.character;

import org.apache.commons.lang3.StringUtils;

public class DesensitizationUtils {
    /**
     * 【银行卡号】
     * 1：前六位、后四位明文展示，其他用星号隐藏每位1个星号
     * 2：不足10位，前六位用明文展示
     *
     * @param bankCard
     * @return
     */
    public static String desensitizedBankCard(String bankCard) {
        if (StringUtils.isBlank(bankCard)) {
            return bankCard;
        }
        if (bankCard.length() < 11) {
            String topThreePhoneNumber = StringUtils.left(bankCard, 6);
            return StringUtils.rightPad(topThreePhoneNumber, StringUtils.length(bankCard), "*");
        }
        return StringUtils.left(bankCard, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(bankCard, 4), StringUtils.length(bankCard), "*"), "******"));
    }

    /**
     * 【身份证号】
     * 1：前六位、后三位明文展示，其他用星号隐藏每位1个星号
     * 2：不足九位，前六位用明文展示
     *
     * @param idCard
     * @return
     */
    public static String desensitizedIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return idCard;
        }

        if (idCard.length() < 10) {
            String topSixPhoneNumber = StringUtils.left(idCard, 6);
            return StringUtils.rightPad(topSixPhoneNumber, StringUtils.length(idCard), "*");
        }

        if (idCard.length() == 15) {
            return idCard.replaceAll("(\\w{6})\\w*(\\w{3})", "$1******$2");
        }
        return idCard.replaceAll("(\\w{6})\\w*(\\w{3})", "$1*********$2");
    }

    /**
     * 【手机号】
     * 1：前三位、后四位明文展示，其他用星号隐藏每位1个星号
     * 2：不足七位，前三位用明文展示
     *
     * @param phoneNumber
     * @return
     */
    public static String desensitizedPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return phoneNumber;
        }
        if (phoneNumber.length() < 8) {
            String topThreePhoneNumber = StringUtils.left(phoneNumber, 3);
            return StringUtils.rightPad(topThreePhoneNumber, StringUtils.length(phoneNumber), "*");
        }
        return phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
    }

    /**
     * 【姓名】
     * 1：前一位明文展示，其他用星号隐藏每位1个星号
     *
     * @param fullName
     * @return
     */
    public static String desensitizedName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return fullName;
        }
        if (fullName.length() == 1) {
            return fullName;
        }
        String firstName = StringUtils.left(fullName, 1);

        return StringUtils.rightPad(firstName, StringUtils.length(fullName), "*");
    }

}
