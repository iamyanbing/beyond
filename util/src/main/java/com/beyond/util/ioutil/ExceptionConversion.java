package com.beyond.util.ioutil;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @Auther: yanbing
 * @Date: 2019/1/30 15:06
 */
public class ExceptionConversion {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionConversion.class);

    public static void exe() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            //针对最开始异常信息被层层封装情况，解析出最开始异常信息并打印
            //获得异常的列表
            List<Throwable> exceptionChain = Throwables.getCausalChain(e);
            //获得根异常
            Throwable rootcause = Throwables.getRootCause(e);
            StringWriter stringWriter = new StringWriter();
            exceptionChain.get(exceptionChain.size() - 1).printStackTrace(new PrintWriter(stringWriter));
            LOGGER.error(stringWriter.toString());
            LOGGER.error("异常信息：", e);
        }
    }
}
