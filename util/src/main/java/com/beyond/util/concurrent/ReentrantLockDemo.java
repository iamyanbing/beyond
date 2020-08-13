package com.beyond.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();


        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1 interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
//                lock.lock();
                lock.lockInterruptibly(); //可以通过interrupt()中断等待获取锁
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t2 interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt()");
        t2.interrupt();

    }
}
