package com.beyond.util.jdkclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author huangyanbing
 * @date 2019-09-09 09:45
 */
public class BigDecimalExplain {
    private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalExplain.class);

    public static void exe() {
        //double、float类型用String实例化，或者用BigDecimal.valueOf(0.01)创建对象
        LOGGER.info("BigDecimal丢失精度的实例化：" + new BigDecimal(0.01));
        LOGGER.info("BigDecimal没有丢失精度的实例化：" + new BigDecimal("0.01"));
        //这是因为源码中调用了Double.toString()，然后再调用了参数类型为String类型的构造函数创建该对象。
        LOGGER.info("BigDecimal.valueOf()创建对象：" + BigDecimal.valueOf(0.01));

        //第一位参数：除数；第二位参数：保留多少位小数；第三位参数：舍入模式，一般用RoundingMode枚举的8个对象
        //RoundingMode.UP：舍入远离零的舍入模式。在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)。注意，此舍入模式始终不会减少计算值的大小。
        //RoundingMode.DOWN：接近零的舍入模式。在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)。注意，此舍入模式始终不会增加计算值的大小。
        //RoundingMode.CEILING：接近正无穷大的舍入模式。如果 BigDecimal 为正，则舍入行为与 ROUNDUP 相同;如果为负，则舍入行为与 ROUNDDOWN 相同。注意，此舍入模式始终不会减少计算值。
        //RoundingMode.FLOOR：接近负无穷大的舍入模式。如果 BigDecimal 为正，则舍入行为与 ROUNDDOWN 相同;如果为负，则舍入行为与 ROUNDUP 相同。注意，此舍入模式始终不会增加计算值。
        //RoundingMode.HALFUP：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。如果舍弃部分 >= 0.5，则舍入行为与 ROUNDUP 相同;否则舍入行为与 ROUND_DOWN 相同。注意，这是我们在小学时学过的舍入模式(四舍五入)。
        //RoundingMode.HALFDOWN：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为上舍入的舍入模式。如果舍弃部分 > 0.5，则舍入行为与 ROUNDUP 相同;否则舍入行为与 ROUND_DOWN 相同(五舍六入)。
        //RoundingMode.HALFEVEN：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。如果舍弃部分左边的数字为奇数，则舍入行为与 ROUNDHALFUP 相同;如果为偶数，则舍入行为与 ROUNDHALF_DOWN 相同。注意，在重复进行一系列计算时，此舍入模式可以将累加错误减到最小。此舍入模式也称为“银行家舍入法”，主要在美国使用。四舍六入，五分两种情况。如果前一位为奇数，则入位，否则舍去。以下例子为保留小数点1位，那么这种舍入方式下的结果。1.15 ==> 1.2 ,1.25 ==> 1.2
        //RoundingMode.UNNECESSARY：断言请求的操作具有精确的结果，因此不需要舍入。如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException。
        LOGGER.info("BigDecimal.divide() 使用：" + new BigDecimal("0.01118").divide(BigDecimal.valueOf(0.1), 3, BigDecimal.ROUND_HALF_DOWN));
        LOGGER.info("BigDecimal.divide() 使用。JDK中也是调用的上面的方法：" + new BigDecimal("0.01118").divide(BigDecimal.valueOf(0.1), 3, RoundingMode.HALF_DOWN));

        //计算差值，再比较
        BigDecimal differencePrice = BigDecimal.valueOf(10).subtract(BigDecimal.valueOf(5));
        if (differencePrice.compareTo(BigDecimal.ZERO) < 0) {

        }
    }
}
