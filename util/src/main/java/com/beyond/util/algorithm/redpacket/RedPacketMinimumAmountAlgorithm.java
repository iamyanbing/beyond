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
 * @description 红包指定最少金额算法（基于红包随机算法实现）
 */
public class RedPacketMinimumAmountAlgorithm {

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
        if (_redPackage.getRemainMoney().compareTo(new BigDecimal("0")) <= 0){
            _redPackage.remainSize--;
            return new BigDecimal("0");
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

    public static void testGetRandomMoneyByBigDecimal(BigDecimal allAmount,BigDecimal minAmount, Integer size){
        RedPackageBigDecimal redPackage =new RedPackageBigDecimal();
        redPackage.setRemainSize(size);
        BigDecimal randomAmount = allAmount.subtract(minAmount.multiply(new BigDecimal(size)));
        redPackage.setRemainMoney(randomAmount);
        List<BigDecimal> amountS = new ArrayList<>();
        while (redPackage.getRemainSize() >= 1){
            BigDecimal  amout = minAmount.add(RedPacketMinimumAmountAlgorithm.getRandomMoneyByBigDecimal(redPackage));
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
        testGetRandomMoneyByBigDecimal(new  BigDecimal("10.00"), new  BigDecimal("1"), 10);
    }

}
