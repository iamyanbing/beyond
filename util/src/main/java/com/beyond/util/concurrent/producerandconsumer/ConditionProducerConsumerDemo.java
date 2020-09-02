package com.beyond.util.concurrent.producerandconsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个生产者线程和10个消费者线程阻塞调用
 *
 * synchronized中wait() 只有一个等待队列；Condition中await() 有多个等待队列（每个Condition一个等待队列）
 * Condition本质就是不同的等待队列
 *
 * Condition可以更加精准的指定哪些线程被唤醒。
 * 比如产品满了，不能再生产，这个时候生产者就唤醒消费者进行消费
 *
 *
 *  Condition是个接口，基本的方法就是await()和signal()方法
 *
 *  Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition()
 *
 * 调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 *
 * Conditon中的await()对应Object的wait()；
 *
 * Condition中的signal()对应Object的notify()；
 *
 * Condition中的signalAll()对应Object的notifyAll()。
 *
 * <pre>
 * await()方法：
 * 调用 Condition 的 await() 方法会使线程进入等待队列，并释放锁，线程状态变为等待状态。
 * 分析源码的大概过程：
 * 1.先将当前线程包装创建为节点。
 * 2.释放当前线程占有的锁，
 * 3.while循环判断当前节点是否存在队列中，
 *  3.1 如果没有则阻塞，继续while判断
 *  3.2如果存在，则退出while循环，执行下面的代码
 * 4.此时说明节点已经在同步队列中，调用acquireQueude方法，重新开始竞争锁，
 * 5.得到锁后返回，退出该方法。
 *
 * signal()方法：
 * 调用 Condition 的 signal() 方法，可以唤醒等待队列的首节点，唤醒之前会将该节点移动到同步队列中。
 * 分析源码的大概过程：
 * 1.先判断当前线程是否有锁，
 * 2.然后对首节点调用doSignal()方法。
 *  2.1 修改首节点，直到获取到为取消的节点，
 *  2.2 调用transferForSignal方法。
 *
 *  signalAll()方法：
 *  将等待队列中的全部节点移动到同步队列中，调用signalAll()方法，循环来唤醒每一个等待队列中的节点，
 *  直到 first 为 null 时，停止循环，并唤醒每个节点的线程。
 *
 * 整体就是调用condition的await()方法后，会将当前线程加入到等待队列中，然后释放锁，然后循环判断节点是否在同步队列中，再获取锁，否则一直阻塞。
 * 调用signal()方法后，先判断当前线程是否有锁，然后调用doSignal()方法，并唤醒线程，被唤醒的线程，再调用acquireQueude()方法，重新开始竞争锁，得到锁后返回，退出该方法。
 * </pre>
 * @param <T>
 */
public class ConditionProducerConsumerDemo<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while(lists.size() == MAX) {
                //想想为什么用while而不是用if？
                //为了排除新线程带来的问题；消费者消耗之后唤醒生产者，这个时候一个新线程抢到了锁，先生产，lists再次到达最大值
                //要写就是while不用if
                producer.await();
            }

            lists.add(t);
            System.out.println("生产完成 ： " + t);
            consumer.signalAll(); //通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while(lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            producer.signalAll(); //通知生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        ConditionProducerConsumerDemo<String> c = new ConditionProducerConsumerDemo<>();
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
