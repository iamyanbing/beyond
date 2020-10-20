package com.beyond.util.jvm.lock;

import com.beyond.util.jvm.bytecode.ByteCode01;

/**
 * jstack观察等待获取锁
 */
public class WaitlockRunningDemo {

    public static void main(String[] args) {
        ByteCode01 b = new ByteCode01();
        Thread thread1 = new Thread(new SynAddRunalbe(b));
        thread1.setName("thread1");
        thread1.start();
        Thread thread2 = new Thread(new SynAddRunalbe(b));
        thread2.setName("thread2");
        thread2.start();
        Thread thread3 = new Thread(new SynAddRunalbe(b));
        thread3.setName("thread3");
        thread3.start();
    }

    public static class SynAddRunalbe implements Runnable {
        ByteCode01 b;

        public SynAddRunalbe(ByteCode01 b) {
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " ： 获取到锁");
                int i = 1;
                while (true) {
                    i++;
                }
            }
        }
    }
}
