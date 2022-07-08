package com.beyond.util.concurrent.container.fromvectortoqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 阻塞队列
 * 容量为0（调用add()方法会报错）
 * SynchronousQueue不是用来装内容的，是专门用来两个线程之间传内容的。
 * 使用场景：用来给其他线程下达任务
 * 和Exchange类概念类似
 *
 * 在线程池中使用较多，线程取任务，互相之间进行调度，就是用的SynchronousQueue。
 */
public class SynchronusQueueConcurrentDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
//        strs.put("bbb");
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}
