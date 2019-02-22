package com.beyond.util.ioutil;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 13:22
 */
public class FileUtilsTest {

    @Ignore
    @Test
    public void printPathOne() throws Exception {
        FileUtils.printPath("../http/HttpJDKUtils.java");
    }

    @Ignore
    @Test
    public void printPathTwo() throws Exception {
        FileUtils.printPath("/etc/profile");
    }

}
