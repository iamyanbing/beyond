package com.beyond.util.concurrent;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 注册机制：与其他barrier不同的是，Phaser中的“注册的同步者（parties）”会随时间而变化，Phaser可以通过构造器初始化parties个数，
 * 也可以在Phaser运行期间随时加入（register）新的parties，以及在运行期间注销（deregister）parties。运行时可以随时加入、注销parties，
 * 只会影响Phaser内部的计数器，它建立任何内部的bookkeeping（账本），因此task不能查询自己是否已经注册了，当然你可以通过实现子类来达成这一设计要求。
 * <p>
 * CyclicBarrier、CountDownLatch需要在初始化的构造函数中指定同步者的个数，且运行时无法再次调整。
 * <p>
 * 同步机制：类似于CyclicBarrier，Phaser也可以awaited多次，它的arriveAndAwaitAdvance()方法的效果类似于CyclicBarrier的await()。
 * Phaser的每个周期（generation）都有一个phase数字，phase 从0开始，当所有的已注册的parties都到达后（arrive）将会导致此phase数字
 * 自增（advance），当达到Integer.MAX_VALUE后继续从0开始。这个phase数字用于表示当前parties所处于的“阶段周期”，
 * 它既可以标记和控制parties的wait行为、唤醒等待的时机。
 * <p>
 * API:
 * register()：新注册一个party，导致Phaser内部registerPaties数量加1；如果此时onAdvance方法正在执行，此方法将会等待它执行完毕后才会返回。此方法返回当前的phase周期数，如果Phaser已经中断，将会返回负数。
 * <p>
 * bulkRegister(int parties)：批量注册多个parties数组，规则同register()。
 * <p>
 * arrive()：到达，阻塞，等到当前phase下其他parties到达。如果没有register（即已register数量为0），调用此方法将会抛出异常，此方法返回当前phase周期数，如果Phaser已经终止，则返回负数。
 * <p>
 * arriveAndDeregister()：到达，并注销一个parties数量，非阻塞方法。注销，将会导致Phaser内部的parties个数减一（只影响当前phase），即下一个phase需要等待arrive的parties数量将减一。异常机制和返回值，与arrive方法一致。
 * <p>
 * arriveAndAwaitAdvance()：到达，且阻塞直到其他parties都到达，且advance。此方法等同于awaitAdvance(arrive())。如果你希望阻塞机制支持timeout、interrupted响应，可以使用类似的其他方法。如果你希望到达后且注销，而且阻塞等到当前phase下其他的parties到达，可以使用awaitAdvance(arriveAndDeregister())方法组合。此方法的异常机制和返回值同arrive()。
 * <p>
 * awaitAdvance(int phase)：阻塞方法，等待phase周期数下其他所有的parties都到达。如果指定的phase与Phaser当前的phase不一致，则立即返回。
 * <p>
 * getArrivedParties()：获取已经到达的parties个数。
 * <p>
 * getPhase()：获取当前phase周期数。如果Phaser已经中断，则返回负值。
 * <p>
 * getRegisteredParties()：获取已经注册的parties个数。
 * <p>
 * getUnarrivedParties()：获取尚未到达的parties个数。
 * <p>
 * onAdvance(int phase,int registeredParties)：这个方法比较特殊，表示当进入下一个phase时可以进行的事件处理，
 * 如果返回true表示此Phaser应该终止（此后将会把Phaser的状态为termination，即isTermination()将返回true。），返回false可以继续进行。
 * phase参数表示当前周期数，registeredParties表示当前已经注册的parties个数。
 * 默认实现为：return registeredParties == 0；在很多情况下，开发者可以通过重写此方法，来实现自定义的advance时间处理机制。
 * <p>
 * 内部原理，比较简单（简述）：
 * 1）两个计数器：分别表示parties个数和当前phase。register和deregister会触发parties变更（CAS），全部parties到达（arrive）会触发phase变更。
 * 2）一个主要的阻塞队列：非AQS实现，对于arriveAndWait的线程，会被添加到队列中并被park阻塞，知道当前phase中最后一个party到达后触发唤醒。
 */
public class PhaserDemo {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();


    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {

            new Thread(new Person("p" + i)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

    }


    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    System.out.println();
                    return false;
                case 4:
                    System.out.println("宝宝出生！" + registeredParties);
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }


    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {

            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);


            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房！\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                //因为这里不会进行阻塞，所以这里面的线程会提前执行baby()中的打印语句
                phaser.arriveAndDeregister();
//                phaser.register()
            }
        }

        public void baby() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 等待baby！\n", name);

                phaser.arriveAndAwaitAdvance();
            } else {
//                phaser.arriveAndDeregister();
                //phaser.register()
            }
            System.out.println("getArrivedParties : " + phaser.getArrivedParties());
            System.out.println("getRegisteredParties : " + phaser.getRegisteredParties());
            System.out.println("getUnarrivedParties : " + phaser.getUnarrivedParties());
            System.out.println("getPhase : " + phaser.getPhase());
        }

        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();

            //再有下一个阶段；已经arriveAndAwaitAdvance()的线程不应该再参与
            //所以要在baby()中做相同的线程过滤
            baby();

        }
    }
}
