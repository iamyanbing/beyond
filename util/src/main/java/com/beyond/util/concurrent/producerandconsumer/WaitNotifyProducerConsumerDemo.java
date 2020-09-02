package com.beyond.util.concurrent.producerandconsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 两个生产者线程和10个消费者线程阻塞调用
 *
 * notifyAll() 唤醒所有阻塞线程
 * @param <T>
 */
public class WaitNotifyProducerConsumerDemo<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素


    public synchronized void put(T t) {
        while(lists.size() == MAX) {
            //想想为什么用while而不是用if？
            //notifyAll() 不知道唤醒的是生产者还是消费者；假如本方法中this.notifyAll()唤醒的是生产者，则用if会有问题
            try {
                this.wait(); //effective java
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        System.out.println("生产完成 ： " + t);
        this.notifyAll(); //通知消费者线程进行消费
    }

    public synchronized T get() {
        T t = null;
        while(lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        this.notifyAll(); //通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        WaitNotifyProducerConsumerDemo<String> c = new WaitNotifyProducerConsumerDemo<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
