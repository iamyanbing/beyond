package com.beyond.util.concurrent.printa1b2;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueuePrintDemo {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        TransferQueue<Character> queue = new LinkedTransferQueue<Character>();
        new Thread(()->{
            try {
                for (char c : aI) {
                    System.out.print(queue.take());//该线程获取第二个线程入队列的数据，然后打印
                    queue.transfer(c);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(()->{
            try {
                for (char c : aC) {
                    queue.transfer(c);
                    System.out.print(queue.take());//该线程获取第一个线程入队列的数据，然后打印
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
