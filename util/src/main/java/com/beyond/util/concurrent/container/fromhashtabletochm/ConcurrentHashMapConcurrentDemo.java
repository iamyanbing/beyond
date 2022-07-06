package com.beyond.util.concurrent.container.fromhashtabletochm;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这个ConcurrentHashMap提高效率主要提高在读上面，由于它往里插的时候内部又做了各种各样的判断，
 * 本来是链表的，到8之后又变成了红黑树，然后里面又做了各种各样的cas的判断，所以他往里插的效率是要更低一些的。
 *
 * HashMap和Hashtable虽然说读的效率会稍微低一些，但是它往里插的时候检查的东西特别的少，就加个锁然后往里一插。
 * 所以，关于效率，还是看你实际当中的需求。本包下用几个简单的小程序来给大家列举了这几个不同的区别。
 *
 * 相比较其他线程安全的类；ConcurrentHashMap特点：插入慢、获取快
 */
public class ConcurrentHashMapConcurrentDemo {

    static Map<UUID, UUID> m = new ConcurrentHashMap<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count/THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for(int i=start; i<start+gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new MyThread(i * (count/THREAD_COUNT));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("插入数据完成时间： " + (end - start));

        System.out.println(m.size());

        //-----------------------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("读取数据完成时间： " + (end - start));
    }
}
