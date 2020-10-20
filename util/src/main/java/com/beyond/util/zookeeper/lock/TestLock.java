package com.beyond.util.zookeeper.lock;

import com.beyond.util.zookeeper.config.ZKUtils;
import org.apache.zookeeper.ZooKeeper;

/**
 * 分布式锁
 * @author: 马士兵教育
 * @create: 2019-09-20 21:21
 */
public class TestLock {


    static ZooKeeper zk ;

    public static void conn (){
        zk  = ZKUtils.getZK();
    }

    public static void close (){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    WatchCallBack watchCallBack = new WatchCallBack();
                    watchCallBack.setZk(zk);
                    String threadName = Thread.currentThread().getName();
                    watchCallBack.setThreadName(threadName);
                    //每一个线程：
                    //抢锁
                    watchCallBack.tryLock();
                    //干活
                    System.out.println(threadName+" working...");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //释放锁
                    watchCallBack.unLock();


                }
            }.start();
        }
        while(true){

        }


    }




}
