package com.beyond.util.ioutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    /**
     * 获取文件MIME
     * @throws Exception
     */
    public static void probeContentType(String filePath) {
        Path path = Paths.get(filePath);
        try {
            LOGGER.info(Files.probeContentType(path));
        } catch (IOException e) {
            LOGGER.error("获取文件MIME类型失败,文件路径:{}, 异常信息:{}", filePath, e.getMessage());
        }
    }
}
