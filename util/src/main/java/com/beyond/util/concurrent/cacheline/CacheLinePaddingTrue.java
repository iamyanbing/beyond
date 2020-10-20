package com.beyond.util.concurrent.cacheline;

/**
 * 读取缓存以cache line为基本单位，目前64bytes
 *
 * T继承Padding，Padding有成员变量p1-p7；所以刚好占64字节。arr两个值刚好在不同缓存行
 *
 * 不存在伪共享，效率高
 */
public class CacheLinePaddingTrue {
    private static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
