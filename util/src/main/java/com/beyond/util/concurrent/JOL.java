package com.beyond.util.concurrent;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class JOL {
    public static void main(String[] args) throws InterruptedException {
        exeBiasedLocking();
//        exeLightweightLocking();
//        exeLightweightLockingBysleep();
    }

    /**
     * 匿名偏向锁->偏向锁
     * 偏向锁默认4秒之后生效。所以线程睡眠5秒，等待偏向锁生效
     *
     * @throws InterruptedException
     */
    public static void exeBiasedLocking() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o= new Object();
        String s= ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
        System.out.println("=========================");
        //因为只有一个线程使用所以还是偏向锁
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 普通对象(无锁)->轻量级锁
     * 创建对象偏向锁未启动（还没有到4秒），所以该对象为无锁。
     * 普通对象在偏向锁未启动情况下直接升级为轻量级锁
     *
     */
    public static void exeLightweightLocking(){
        Object o= new Object();
        String s= ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
        System.out.println("=========================");
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }


    /**
     * 普通对象(无锁)->轻量级锁
     * 普通对象睡眠5秒后还是升级为轻量级锁，不会升级成偏向锁
     * @throws InterruptedException
     */
    public static void exeLightweightLockingBysleep() throws InterruptedException {
        Object o= new Object();
        String s= ClassLayout.parseInstance(o).toPrintable();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(s);
        System.out.println("=========================");
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
