package com.beyond.util.jvm.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * 打印类加载器
 */
public class ClassLoaderLevel {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderLevel.class);
    public static void main(String[] args) {
        //<JAVA_ HOME>\lib路径下类通过Bootstrap ClassLoader加载，Bootstrap 类加载器是用 C++ 实现的，是虚拟机自身的一部分，如果获取它的对象，将会返回 null
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        //<JAVA_ HOME>\lib\ext路径下类通过 Extension ClassLoader加载
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        //ClassPath中的类库，由系统参数java.class.path指定；该路径下的类由 Application ClassLoader加载
        System.out.println(ClassLoaderLevel.class.getClassLoader());
        System.out.println("-----getParent()--------");
        System.out.println(ClassLoaderLevel.class.getClassLoader().getParent());
        System.out.println(ClassLoaderLevel.class.getClassLoader().getParent().getParent());
//        System.out.println(ClassLoaderLevel.class.getClassLoader().getParent().getParent().getParent());//报错

        // 获取根类加载器所加载的全部URL数组
        URL[] bootstrapUrls = Launcher.getBootstrapClassPath().getURLs();
        LOGGER.info("*********根类加载器加载的全部URL*************");
        for (URL bootstrap : bootstrapUrls) {
            LOGGER.info(bootstrap.toExternalForm());
        }

        System.out.println("------------");
        //加载器都是被Bootstrap ClassLoader加载
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());


//        System.out.println(new ClassLoaderLevel().getParent());
//        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
