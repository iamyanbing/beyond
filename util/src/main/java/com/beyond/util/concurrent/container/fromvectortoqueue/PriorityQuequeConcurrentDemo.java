package com.beyond.util.concurrent.container.fromvectortoqueue;

import java.util.PriorityQueue;

/**
 * 非阻塞队列
 *
 * PriorityQueue特点是它内部你往里装的时候并不是按顺序往里装的，而是内部进行了一个排序。
 * 元素大小的评判可以通过元素本身的自然顺序，也可以通过构造时传入的Comparator比较。
 *
 * PriorityQueue作用是能保证每次取出的元素都是队列中权值最小的。
 *
 * 它内部实现的结构是一个二叉树，这个二叉树可以认为是堆排序里面的那个最小堆值排在最上面。
 * 即底层实现是二叉树，会进行排序
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
