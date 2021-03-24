package com.beyond.util.algorithm.redpacket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 参考：
 * https://www.jianshu.com/p/026ceece4d80
 * https://www.zhihu.com/question/22625187
 * @Author huangyanbing
 * @Date 2021/2/15 18:34
 * @description 红包随机算法
 */
public class RedPacketStochasticAlgorithm {

    /**
     * 红包里的金额怎么算？为什么出现各个红包金额相差很大？
     * 答：随机，额度在0.01和剩余平均值*2之间。
     * @param _redPackage
     * @return
     */
    public static BigDecimal getRandomMoneyByBigDecimal(RedPackageBigDecimal _redPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_redPackage.remainSize == 1) {
            _redPackage.remainSize--;
            return new BigDecimal(Math.round(_redPackage.remainMoney.multiply(new BigDecimal("100")).doubleValue())).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP) ;
        }
        Random r = new Random();
        BigDecimal min = new BigDecimal("0.01");
        BigDecimal max = _redPackage.remainMoney.divide(new BigDecimal(_redPackage.remainSize), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("2"));
        BigDecimal money = new BigDecimal(r.nextDouble()).multiply(max);
        money = money.compareTo(min) <= 0 ? min : money;
        money = new BigDecimal(Math.floor(money.multiply(new BigDecimal("100")).doubleValue())).divide(new BigDecimal("100"),2, RoundingMode.HALF_UP);
        _redPackage.remainSize--;
        _redPackage.remainMoney = _redPackage.remainMoney.subtract(money);
        return money;
    }

    public static void testGetRandomMoneyByBigDecimal(){
//        System.out.println(Math.round(11.115));
//        System.out.println(Math.round(11.15));
//        System.out.println(Math.round(11.45));
//        System.out.println(Math.round(11.55));
//        System.out.println(Math.round(11.54));
//        System.out.println(Math.floor(22.22));
//        System.out.println(Math.floor(22.99));
        RedPackageBigDecimal redPackage =new RedPackageBigDecimal();
        redPackage.setRemainSize(10);
        redPackage.setRemainMoney(new BigDecimal("0.04"));
        List<BigDecimal> amountS = new ArrayList<>();
        while (redPackage.getRemainSize() >= 1){
            BigDecimal  amout = RedPacketStochasticAlgorithm.getRandomMoneyByBigDecimal(redPackage);
            amountS.add(amout);
            System.out.println(amout);
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal amout:
                amountS) {
            sum = sum.add(amout);
        }

        System.out.println("sum = " + sum);
    }

    public static void main(String[] args) {
        testGetRandomMoneyByBigDecimal();
//        testGetRandomMoneyByDouble();
    }

    public static void testGetRandomMoneyByDouble(){
        RedPackageDouble redPackage =new RedPackageDouble();
        redPackage.setRemainSize(11);
        redPackage.setRemainMoney(0.11);
        List<Double> amountS = new ArrayList<>();
        while (redPackage.getRemainSize() >= 1){
            Double  amout = RedPacketStochasticAlgorithm.getRandomMoneyDouble(redPackage);
            amountS.add(amout);
            System.out.println(amout);
        }

        Double sum = Double.valueOf("0");
        for (Double amout:
                amountS) {
            sum = sum + amout;
        }

        System.out.println("sum = " + sum);
    }

    public static double getRandomMoneyDouble(RedPackageDouble _redPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_redPackage.remainSize == 1) {
            _redPackage.remainSize--;
            return (double) Math.round(_redPackage.remainMoney * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01;
        double max   = _redPackage.remainMoney / _redPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        _redPackage.remainSize--;
        _redPackage.remainMoney -= money;
        return money;
    }
}
