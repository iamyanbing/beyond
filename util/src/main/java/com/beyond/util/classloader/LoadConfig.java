package com.beyond.util.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Auther: yanbing
 * @Date: 2019/2/27 18:23
 */
public class LoadConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadConfig.class);

    public static final String CONFIG_FILE_NAME = "config.properties";

    public static void load() {
        ClassLoader cl = LoadConfig.class.getClassLoader();
        try {
            //从classpath中获取属性文件，作为配置入口
            Enumeration<URL> en = cl.getResources(LoadConfig.CONFIG_FILE_NAME);
            if (en != null) {
                while (en.hasMoreElements()) {
                    URL url = en.nextElement();
                    //加载属性文件
                    Properties properties = new Properties();
                    properties.load(url.openStream());
                    //Mysql配置加载
                    LOGGER.info(properties.getProperty("mysql.host.ip"));
                    LOGGER.info(properties.getProperty("mysql.host.port"));
                    LOGGER.info(properties.getProperty("mysql.host.database"));
                    LOGGER.info(properties.getProperty("mysql.host.username"));
                    LOGGER.info(properties.getProperty("mysql.host.password"));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
