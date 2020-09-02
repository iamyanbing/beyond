package com.beyond.util.concurrent.container.fromvectortoqueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Vector线程安全，表示调用Vector对象方法是线程安全，但是在执行while语句并不是线程安全
 */
public class VectorConcurrentDemo {
    static Vector<String> tickets = new Vector<>();


    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {

        for(int i=0; i<10; i++) {
            new Thread(()->{
                //调用size()、remove(0)线程安全，while方法不是线程安全
                while(tickets.size() > 0) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
