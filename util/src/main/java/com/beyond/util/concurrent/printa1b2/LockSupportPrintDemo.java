package com.beyond.util.concurrent.printa1b2;

import java.util.concurrent.locks.LockSupport;

public class LockSupportPrintDemo {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws Exception {
        char[] aI = "ABCDEFG".toCharArray();
        char[] aC = "1234567".toCharArray();


        t1 = new Thread(() -> {

            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2); //叫醒T2
                LockSupport.park(); //T1阻塞
            }

        }, "t1");

        t2 = new Thread(() -> {

            for (char c : aC) {
                LockSupport.park(); //t2阻塞
                System.out.print(c);
                LockSupport.unpark(t1); //叫醒t1
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
