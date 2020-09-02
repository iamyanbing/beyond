package com.beyond.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * <pre>
 * 　　那么怎么使用Callable呢？一般情况下是配合ExecutorService来使用的，在ExecutorService接口中声明了若干个submit方法的重载版本：
 *
 *  <T> Future<T> submit(Callable<T> task);
 *  <T> Future<T> submit(Runnable task, T result);
 *  Future<?> submit(Runnable task);
 *
 * 　　第一个submit方法里面的参数类型就是Callable。
 * 　　Callable一般是和ExecutorService配合来使用。
 * 　　一般情况下我们使用第一个submit方法和第三个submit方法，第二个submit方法很少使用。
 * </pre>
 *
 * @Auther: yanbing
 * @Date: 2018/8/22 17:41
 */
public class ThreadPoolHaveResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolHaveResult.class);

    /**
     * Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
     * <ul>
     * 在Future接口中声明了5个方法，下面依次解释每个方法的作用：
     * <li>cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。参数mayInterruptIfRunning表示是否允许取消正在执行却没有执行完毕的任务，如果设置true，则表示可以取消正在执行过程中的任务。如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取消已经完成的任务会返回false；如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false；如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。</li>
     * <li>isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。</li>
     * <li>isDone方法表示任务是否已经完成，若任务完成，则返回true；</li>
     * <li>get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；</li>
     * <li>get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。</li>
     * </ul>
     * <pre>
     * 也就是说Future提供了三种功能：
     * 1）判断任务是否完成；
     * 2）能够中断任务；
     * 3）能够获取任务执行结果。
     * </pre>
     * 因为Future只是一个接口，所以是无法直接用来创建对象使用的，因此就有了下面的FutureTask。
     */
    public static void executeFuture() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);//异步
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        LOGGER.info("主线程在执行任务");
        try {
            LOGGER.info("task运行结果" + result.get());//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        LOGGER.info("所有任务执行完毕");
    }

    /**
     * 可以看出RunnableFuture继承了Runnable接口和Future接口，而FutureTask实现了RunnableFuture接口。
     * 所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。
     */
    public static void executeFutureTask() {
        //第一种方式,使用ExecutorService
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        executor.submit(futureTask);
//        executor.shutdown();

        //第二种方式，使用的是Thread。注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        LOGGER.info("主线程在执行任务");

        try {
            LOGGER.info("task运行结果" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LOGGER.info("所有任务执行完毕");
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return sum;
    }
}
