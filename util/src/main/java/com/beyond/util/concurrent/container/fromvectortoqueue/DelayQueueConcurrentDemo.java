package com.beyond.util.concurrent.container.fromvectortoqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 是BlockingQueue的一种
 * 也是阻塞队列
 *
 * DelayQueue可以实现在时间上的排序
 *
 * 这个阻塞队列装任务的时候要求必须实现Delayed接口，
 * Delayed需要做一个比较compareTo，时间等待越短的就会优先得到运行，实现Comparable接口重写 compareTo方法来确定你这个任务之间是怎么排序的。
 * getDelay去拿到你Delay多长时间了。
 *
 * DelayQueueConcurrentDemo代码逻辑：
 * 往里头装任务的时候首先拿到当前时间，在当前时间的基础之上指定在多长时间之后这个任务要运行。
 * 但是当我们去拿的时候，一般的队列是先进先出。这个队列是不一样的，按时间进行排序（按紧迫程度进行排序）。
 *
 * 使用场景：
 * DelayQueue就是按照时间进行任务调度。
 *
 * DelayQueue本质上用的是PriorityQueue；底层实现是用PriorityQueue
 */
public class DelayQueueConcurrentDemo {

    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        MyTask(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {

            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }


        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 2000);
        MyTask t3 = new MyTask("t3", now + 1500);
        MyTask t4 = new MyTask("t4", now + 2500);
        MyTask t5 = new MyTask("t5", now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for(int i=0; i<5; i++) {
            System.out.println(tasks.take());
        }
    }
}
