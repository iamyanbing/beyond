package com.beyond.util.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 当对象被回收时；通过Queue可以检测到；然后清理堆外内存
 * <p>
 * 主要运用在内存溢出
 * <p>
 * 使用场景：管理堆外内存
 * </p>
 * <p>
 * 堆外内存：不被JVM管理的内存，即被操作系统管理的内存。比如NIO中的DirectByteBuffer。
 * 堆外内存就是把内存对象分配在Java虚拟机的堆以外的内存，这些内存直接受操作系统管理（而不是虚拟机），这样做的结果就是能够在一定程度上减少垃圾回收对应用程序造成的影响。
 * 作为JAVA开发者我们经常用java.nio.DirectByteBuffer对象进行堆外内存的管理和使用，它会在对象创建的时候就分配堆外内存。
 * DirectByteBuffer类是在Java Heap外分配内存，对堆外内存的申请主要是通过成员变量unsafe来操作。
 * <p>
 * 堆外内存常见使用技术：netty
 * <p>
 * PhantomReference ： 虚引用基本不用；是给写虚拟机JVM、框架的程序员用的
 * <p>
 * 当垃圾回收器准备回收一个对象时，如果发现它还有引用（虚引用也是引用），那么就会在回收对象之前，把这个引用加入到与之关联的引用队列中去。
 * 程序可以通过判断引用队列中是否已经加入了引用，来判断被引用的对象是否将要被垃圾回收，这样就可以在对象被回收之前采取一些必要的措施。
 */
public class PhantomReferenceDemo {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<ReferenceObject> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        PhantomReference<ReferenceObject> phantomReference = new PhantomReference<>(new ReferenceObject(), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends ReferenceObject> poll = QUEUE.poll();
                if (poll != null) {
                    if (poll.get() != null) {
                        //不能直接获取到new ReferenceObject()对象；可以通过其他方式获取
                        poll.get().print();
                    }
                    //下面打印出的是同一个对象
                    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
                    System.out.println(phantomReference);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
