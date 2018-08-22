package com.beyond.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * <pre>
 * CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
 * 而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
 * 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
 *
 * Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。</pre>
 *
 * @Auther: yanbing
 * @Date: 2018/8/22 23:02
 */
public class Auxiliary {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolHaveResult.class);

    /**
     * CountDownLatch可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了
     * <ul>
     * CountDownLatch类只提供了一个构造器：
     * <li>public CountDownLatch(int count) {  };  //参数count为计数值</li>
     * </ul>
     * <ul>
     * 然后下面这3个方法是CountDownLatch类中最重要的方法：
     * <li>public void await() throws InterruptedException { };//调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行</li>
     * <li>public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };//和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行</li>
     * <li>public void countDown() { };//将count值减1</li>
     * </ul>
     */
    public static void executeCountDownLatch() {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(new TastCountDownLatch(latch)).start();

        new Thread(new TastCountDownLatch(latch)).start();

        try {
            LOGGER.info("等待2个子线程执行完毕...");
            latch.await();
            LOGGER.info("2个子线程已经执行完毕");
            LOGGER.info("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
     * <ul>
     * CyclicBarrier类位于java.util.concurrent包下，CyclicBarrier提供2个构造器
     * <li>public CyclicBarrier(int parties, Runnable barrierAction)</li>
     * <li>public CyclicBarrier(int parties)</li>
     * 参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
     * </ul>
     * <ul>
     * CyclicBarrier中最重要的方法就是await方法，它有2个重载版本
     * <li>public int await() throws InterruptedException, BrokenBarrierException</li>
     * <li>public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException</li>
     * <p>第一个版本比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
     * <p>第二个版本是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。
     * </ul>
     *
     * @throws Exception
     */
    public static void executeCyclicBarrier() throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程" + Thread.currentThread().getName() + "到达barrier状态");
            }
        });

        new Thread(new TastCyclicBarrier(barrier, latch)).start();
        new Thread(new TastCyclicBarrier(barrier, latch)).start();
        new Thread(new TastCyclicBarrier(barrier, latch)).start();
        latch.await();
    }

    /**
     * Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
     * <ul>
     * <li>public Semaphore(int permits);参数permits表示许可数目，即同时可以允许多少线程进行访问</li>
     * <li>public Semaphore(int permits, boolean fair);这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可</li>
     * </ul>
     * <ul>
     * <li>public void acquire() throws InterruptedException;获取一个许可</li>
     * <li>public void acquire(int permits) throws InterruptedException;获取permits个许可</li>
     * <li>public void release();释放一个许可</li>
     * <li>public void release(int permits);释放permits个许可</li>
     * <p>acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
     * <p>release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
     * <p>上面4个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法：
     * <li>public boolean tryAcquire();尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false</li>
     * <li>public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException;尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false</li>
     * <li>public boolean tryAcquire(int permits);尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false</li>
     * <li>public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException;尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false</li>
     * </ul>
     *
     * @throws Exception
     */
    public static void executeSemaphore() throws Exception {
        Semaphore semaphore = new Semaphore(2);
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(new TastSemaphore(semaphore, latch)).start();
        new Thread(new TastSemaphore(semaphore, latch)).start();
        new Thread(new TastSemaphore(semaphore, latch)).start();
        latch.await();
    }
}

class TastSemaphore implements Runnable {

    Semaphore semaphore;
    CountDownLatch latch;

    public TastSemaphore(Semaphore semaphore, CountDownLatch latch) {
        this.semaphore = semaphore;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(1000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕,等待其他线程写入完毕");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

class TastCyclicBarrier implements Runnable {

    CyclicBarrier cyclicBarrier;
    CountDownLatch latch;

    public TastCyclicBarrier(CyclicBarrier cyclicBarrier, CountDownLatch latch) {
        this.cyclicBarrier = cyclicBarrier;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(1000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕,等待其他线程写入完毕");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程写入完毕，继续处理其他任务...");
        latch.countDown();
    }
}

class TastCountDownLatch implements Runnable {

    CountDownLatch latch;

    public TastCountDownLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(3000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}