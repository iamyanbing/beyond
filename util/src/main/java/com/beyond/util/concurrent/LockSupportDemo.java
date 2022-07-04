package com.beyond.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park()：当前线程阻塞(线程阻塞，和锁的概念无关)
 * LockSupport.unpark(t)：唤醒某个指定线程
 *
 * 使用起来比wait()、condition更灵活
 *
 *
 * 淘宝面试题：实现一个容器，提供两个方法，add、size。
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 *
 * VolatileDemo、LockSupportDemo、WaitNotifyFailDemo、WaitNotifySuccessDemo一样
 * 使用技术不一样，所以得到结果也不一样 有的成功 有的失败
 */
public class LockSupportDemo {
    // 添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        LockSupportDemo c = new LockSupportDemo();

        t1 = new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if (c.size() == 5) {
                    System.out.println("t1 park");
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            System.out.println("t2启动");
            //if (c.size() != 5) {
            System.out.println("t2 park");
            LockSupport.park();

            //}
            System.out.println("t2 结束");
            LockSupport.unpark(t1);


        }, "t2");

        t2.start();
        t1.start();


        //下面是另外一个程序
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        //unpark(t)可以先于park()调用，再调用park()时则不会阻塞。
        //使用场景：当某个状态改变是，可以先调用unpark(t),让后面线程中调用park()不在阻塞
        LockSupport.unpark(t);

//        try {
//            TimeUnit.SECONDS.sleep(8);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("after 8 senconds!");
//
//        LockSupport.unpark(t);

    }
}
