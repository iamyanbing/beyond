package com.beyond.util.concurrent;

/**
 * @Auther: yanbing
 * @Date: 2018/8/23 22:49
 */
public class ThreadLocalExplain {
    public static void execute() {
        ThreadLocal threadLocal=new ThreadLocal();
        threadLocal.get();
        threadLocal.set(null);
    }
}
