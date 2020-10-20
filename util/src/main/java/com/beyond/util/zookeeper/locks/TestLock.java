package com.beyond.util.zookeeper.locks;

import com.beyond.util.zookeeper.configurationcenter.DefaultWatch;
import com.beyond.util.zookeeper.configurationcenter.ZKConf;
import com.beyond.util.zookeeper.configurationcenter.ZKUtils;
import org.apache.zookeeper.ZooKeeper;


/**
 * @author: 马士兵教育
 * @create: 2019-09-20 16:14
 */
public class TestLock {


    static ZooKeeper zk;
    static ZKConf zkConf;
    static DefaultWatch defaultWatch;

    public static void conn() {
        zkConf = new ZKConf();
        zkConf.setAddress("192.168.150.11:2181,192.168.150.12:2181,192.168.150.13:2181,192.168.150.14:2181/testLock");
        zkConf.setSessionTime(1000);
        defaultWatch = new DefaultWatch();
        ZKUtils.setConf(zkConf);
        ZKUtils.setWatch(defaultWatch);
        zk = ZKUtils.getZK();
    }

    public static void close() {
        ZKUtils.closeZK();
    }

    public static void main(String[] args) {

        conn();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    WatchCallBack watchCallBack = new WatchCallBack();
                    watchCallBack.setZk(zk);
                    String name = Thread.currentThread().getName();
                    watchCallBack.setThreadName(name);

                    try {
                        //tryLock
                        watchCallBack.tryLock();
                        System.out.println(name + " at work");
                        watchCallBack.getRootData();
//                        Thread.sleep(1000);
                        //unLock
                        watchCallBack.unLock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }

        close();

        while (true) {

        }

    }
}
