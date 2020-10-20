package com.beyond.util.jvm.lock;

import com.beyond.util.jvm.bytecode.ByteCode01;

/**
 * jstack观察死锁
 */
public class DeadlockDemo {

    public static void main(String[] args) {
        ByteCode01 b1 = new ByteCode01();
        ByteCode01 b2 = new ByteCode01();
        Thread thread1 = new Thread(new SynAddRunalbe(b1, b2, 1, 2, true));
        thread1.setName("thread1");
        thread1.start();
        Thread thread2 = new Thread(new SynAddRunalbe(b1, b2, 2, 1, false));
        thread2.setName("thread2");
        thread2.start();
    }

    /**
     * 线程死锁等待演示
     */
    public static class SynAddRunalbe implements Runnable {
        ByteCode01 b1, b2;
        int a, b;
        boolean flag;

        public SynAddRunalbe(ByteCode01 b1, ByteCode01 b2, int a, int b, boolean flag) {
            this.b1 = b1;
            this.b2 = b2;
            this.a = a;
            this.b = b;
            this.flag = flag;
        }

        @Override
        public void run() {
            try {
                if (flag) {
                    synchronized (b1) {
                        Thread.sleep(100);
                        synchronized (b2) {
                            System.out.println(a + b);
                        }
                    }
                } else {
                    synchronized (b2) {
                        Thread.sleep(100);
                        synchronized (b1) {
                            System.out.println(a + b);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
