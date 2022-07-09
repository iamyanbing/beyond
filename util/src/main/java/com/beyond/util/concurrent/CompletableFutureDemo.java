package com.beyond.util.concurrent;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 假设你能提供一个服务
 * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 * 比如在自己网站上展示京东、天猫等电商平台同一类商品价格。eg：查询iphone在天猫、京东上的价格，并展示在自己网站上。
 *
 * 面试不问，作为了解
 *
 * CompletableFuture，管理多个Future的结果。
 * CompletableFuture他的底层用的是ForkJoinPool。
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start, end;

        /*start = System.currentTimeMillis();

        priceOfTM();
        priceOfTB();//问题：priceOfTB()执行有异常，或者该方法执行很慢。
        priceOfJD();

        end = System.currentTimeMillis();
        System.out.println("use serial method call! " + (end - start));*/

        //上面注释方法为串行，时间长、效率低
        //下面方法为多线程执行

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());//异步执行
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());

        //阻塞等待前面所有supplyAsync方法执行完成。等于Future类的get()
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();

        //阻塞等待,只要前面有一个supplyAsync方法执行完成。主程序往下执行
//        CompletableFuture.anyOf(futureTM, futureTB, futureJD).join();

        CompletableFuture.supplyAsync(()->priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str-> "price " + str)
                .thenAccept(System.out::println);


        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
