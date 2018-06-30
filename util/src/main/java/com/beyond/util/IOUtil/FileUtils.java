package com.beyond.util.IOUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 13:17
 */
public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * <p>getPath ： 获取File构造器中传递的参数，不管参数是什么，都原样返回</p>
     * <p>getAbsolutePath ： 获取绝对路径；如果File构造器中参数是相对路径，则返回当前项目所在路径+File构造器中参数是相对路径</p>
     * <p>getCanonicalPath ：会对路径进行解析，比如软连接会定位到真正的位置</p>
     *
     * @param path
     *
     * @throws Exception
     */
    public static void printPath(String path) throws Exception {
        File file = new File(path);
        LOGGER.info("file.exists()             :" + file.exists());
        LOGGER.info("file.getPath()            :" + file.getPath());
        LOGGER.info("file.getAbsolutePath()    :" + file.getAbsolutePath());
        LOGGER.info("file.getCanonicalPath()   :" + file.getCanonicalPath());
        LOGGER.info("file.getParent()          :" + file.getParent());
    }
}
