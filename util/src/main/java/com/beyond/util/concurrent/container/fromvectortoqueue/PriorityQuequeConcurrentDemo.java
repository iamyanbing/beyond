package com.beyond.util.concurrent.container.fromvectortoqueue;

import java.util.PriorityQueue;

/**
 * 阻塞队列
 *
 * 底层实现是二叉树，会进行排序
 */
public class PriorityQuequeConcurrentDemo {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }

    }
}
