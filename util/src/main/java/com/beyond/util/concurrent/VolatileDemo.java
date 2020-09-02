package com.beyond.util.concurrent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * volatile内存可见性
 * volatile 一般修饰简单数据类型
 * volatile 修饰引用数据类型时，只有当引用变了才会实时更新；引用对象的值变化，不会实时更新
 *
 * 结论：在没有把握的情况下不要用volatile，要使用也是用来修饰简单数据类型
 *
 * 对象的内容值变化时，线程之间通信一般使用wait/notify、LockSupport
 */
public class VolatileDemo {
//        private static volatile boolean flag = true;
    private static boolean flag = true;

    //添加volatile，使t2能够得到通知
    //volatile List lists = new LinkedList();
    volatile List lists = Collections.synchronizedList(new LinkedList<>());

    public static void main(String[] args) throws InterruptedException {
//        threadVisibility();

        //t2 结束 的打印并不一定会是想要的结果。因为volatile修饰时引用对象的值变化，不会实时更新
        threadVisibilityReferenceType();
    }

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void threadVisibilityReferenceType(){
        VolatileDemo c = new VolatileDemo();
        new Thread(() -> {
            for(int i=0; i<40; i++) {
                c.add(new Object());
                System.out.println("add " + i);
            }
        }, "t1").start();

        new Thread(() -> {
            while(true) {
                System.out.println("size " + c.size());
                if(c.size() == 20) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }

    public static void threadVisibility(){
        new Thread(() -> {
            System.out.println("start");
            while (flag) {
                //这条语句打开，flag前面没有volatile程序也会停止。因为System.out会把内存进行同步(有一个刷内存的过程)
                System.out.println("do");
            }
            System.out.println("end");
        }, "server").start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
    }
}
