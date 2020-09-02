package com.beyond.util.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用：只要JVM gc就会把对象回收掉
 *
 * 每次GC都会把弱引用回收，弱引用作用在哪里？
 * 如果有一个强引用指向这个对象（同时被弱引用指向的对象），只要强引用消失，这个对象就不用再管，gc自动回收。参考ThreadLocal
 *
 * 同时还可以参考WeakHashMap这个类
 *
 * 使用场景：容器
 */
public class WeakReferenceDemo {
    private static List<Object> list = new ArrayList<>();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            WeakReference<byte[]> sr = new WeakReference<>(buff);
            list.add(sr);
        }
        printObject(list);

        System.gc();
        System.out.println("垃圾回收执行完毕");

        printObject(list);
    }

    public static void printObject(List<Object> list){
        for(int i=0; i < list.size(); i++){
            Object obj = ((WeakReference) list.get(i)).get();
            System.out.println(obj);
        }
    }
}
