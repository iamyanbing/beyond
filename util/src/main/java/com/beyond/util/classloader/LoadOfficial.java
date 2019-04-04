package com.beyond.util.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * 类加载器分类
 * <ul>
 * <li>Bootstrap ClassLoader：根类加载器</li>
 * <li>Extension ClassLoader：扩展类加载器</li>
 * <li>System ClassLoader：应用程序类加载器</li>
 * <li>用户自定义类加载器</li>
 * </ul>
 *
 * @Auther: yanbing
 * @Date: 2019/2/28 10:51
 */
public class LoadOfficial {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadConfig.class);

    public static void load() {
        LOGGER.info("*********打印各类加载器名字*************");
        LOGGER.info(LoadOfficial.class.getClassLoader() + "");
        LOGGER.info(LoadOfficial.class.getClassLoader().getParent() + "");
        LOGGER.info(LoadOfficial.class.getClassLoader().getParent().getParent() + "");

        // 获取根类加载器所加载的全部URL数组
        URL[] bootstrapUrls = Launcher.getBootstrapClassPath().getURLs();
        LOGGER.info("*********根类加载器加载的全部URL*************");
        for (URL bootstrap : bootstrapUrls) {
            LOGGER.info(bootstrap.toExternalForm());
        }

    }
}
