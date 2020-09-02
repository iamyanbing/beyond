package com.beyond.util.reference;

import java.lang.ref.SoftReference;

/**
 * 测试本demo需要重置虚拟机堆内存（最后打印结果为null）
 * -Xms20M -Xmx20M
 *
 * 在内存足够的时候，软引用对象不会被回收，只有在内存不足时，系统则会回收软引用对象，如果回收了软引用对象之后仍然没有足够的内存，才会抛出内存溢出异常
 *
 * 使用场景：缓存（现在缓存用 Redis）
 * 一个大数据对象使用SoftReference做缓存，当内存大小足够时，则该数据对象存储在内存中，下次可以直接获取；
 * 如果内存大小不够时，则把这个大数据对象gc掉，
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        //m = null;
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        //再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024*1024*10];
        System.out.println(m.get());
    }
}
