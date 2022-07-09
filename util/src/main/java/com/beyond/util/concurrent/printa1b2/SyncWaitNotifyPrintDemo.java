package com.beyond.util.concurrent.printa1b2;

import java.util.concurrent.CountDownLatch;

/**
 * wait()、notify()想要按照顺序打印，需要借助其他工具类CountDownLatch
 * 必须按照A1B2C3D4E5F6G7顺序输出，字母先输出 数字后输出
 *
 * SyncWaitNotifyPrintDemo、LockSupportPrintDemo必须掌握
 */
public class SyncWaitNotifyPrintDemo {
    private static volatile boolean t2Started = false;

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();


        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            //latch.await();

            synchronized (o) {

                //该循环可以替代CountDownLatch工具类
                while (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

//                o.notify();//必须，否则无法停止程序。每个线程最后o.notify()只需要一个，只需要加在最先输出的线程即可，用两个也不会出错
            }
        }, "t1").start();

        new Thread(() -> {

            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
//                    latch.countDown();
                    t2Started = true;
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则无法停止程序。每个线程最后o.notify()只需要一个，只需要加在最先输出的线程即可，用两个也不会出错
            }
        }, "t2").start();
    }
}
