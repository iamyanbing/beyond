package com.beyond.util.zookeeper.config;

import org.apache.zookeeper.ZooKeeper;


/**
 * @author: 马士兵教育
 * @create: 2019-09-20 20:07
 */
public class TestConfig {


    static ZooKeeper zk;


    public static void conn() {
        zk = ZKUtils.getZK();
    }

    public static void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        conn();

        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setZk(zk);
        MyConf myConf = new MyConf();
        watchCallBack.setConf(myConf);

        watchCallBack.aWait();
        //1，节点不存在
        //2，节点存在

        while (true) {

            if (myConf.getConf().equals("")) {
                System.out.println("conf diu le ......");
                watchCallBack.aWait();
            } else {
                System.out.println(myConf.getConf());

            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
