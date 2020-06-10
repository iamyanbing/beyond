package com.beyond.util.concurrent;

/**
 * 用于看字节码文件
 * monitorenter monitorexit
 */
public class Sysnchorized {
    public synchronized void m() {
        System.out.printf("Sysnchorized");
    }

    public void m2() {
        synchronized(this) {
            System.out.printf("Sysnchorized");
        }
    }

    public void m3() {
        Integer integer=new Integer(1);
        synchronized(integer) {
            System.out.printf("Sysnchorized");
        }
    }
}
