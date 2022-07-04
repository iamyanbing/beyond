package com.beyond.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * notify并不释放锁，只是唤醒其他线程来竞争锁，当synchronized代码执行完才释放锁。
 *
 *
 * 淘宝面试题：实现一个容器，提供两个方法，add、size。
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束。
 *
 * VolatileDemo、LockSupportDemo、WaitNotifyFailDemo、WaitNotifySuccessDemo一样
 * 使用技术不一样，所以得到结果也不一样 有的成功 有的失败
 */
public class WaitNotifyFailDemo {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        WaitNotifyFailDemo c = new WaitNotifyFailDemo();

        final Object lock = new Object();

        new Thread(() -> {
            synchronized(lock) {
                System.out.println("t2启动");
                if(c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized(lock) {
                for(int i=0; i<10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if(c.size() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

    }
}
