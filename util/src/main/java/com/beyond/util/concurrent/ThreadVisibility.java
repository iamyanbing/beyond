package com.beyond.util.concurrent;

/**
 * volatile内存可见性
 */
public class ThreadVisibility {
//        private static volatile boolean flag = true;
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("start");
            while (flag) {
                //这条语句打开，flag前面没有volatile程序也会停止。因为System.out会把内存进行同步(有一个刷内存的过程)
                System.out.println("do");
            }
            System.out.println("end");
        }, "server").start();


        Thread.sleep(1000);

        flag = false;
    }
}
