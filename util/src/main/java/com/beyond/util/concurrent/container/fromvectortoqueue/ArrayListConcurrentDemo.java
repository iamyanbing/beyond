package com.beyond.util.concurrent.container.fromvectortoqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 同一张票可能会被销售多次
 *
 * 为什么最后会出现null？和报异常信息？
 * 分析源码，从内存可见性考虑
 */
public class ArrayListConcurrentDemo {
    static List<String> tickets = new ArrayList<>();

    static {
        for(int i=0; i<1000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread().getName() + "销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
