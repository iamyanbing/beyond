package com.beyond.util.concurrent.cacheline;

/**
 * 读取缓存以cache line为基本单位，目前64bytes
 *
 * 位于同一缓存行的两个不同数据，被两个不同CPU锁定，产生互相影响的伪共享问题
 *
 * long占8直接；所以arr占16个直接；因为缓存行为64bytes，所以arr数组两个值在同一个缓存行
 *
 * 存在伪共享，效率低
 *
 * 伪共享问题解决方法：使用缓存行的对齐能够提高效率，但是会浪费空间
 */
public class CacheLinePaddingFalse {
    private static class T {
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
