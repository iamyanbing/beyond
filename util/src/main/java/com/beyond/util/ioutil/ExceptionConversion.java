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
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64ToPDF.class);

    public static void exe() throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            //获得异常的列表
            List<Throwable> exceptionChain = Throwables.getCausalChain(e);
            //获得根异常
            Throwable rootcause = Throwables.getRootCause(e);
            StringWriter stringWriter = new StringWriter();
            exceptionChain.get(exceptionChain.size() - 1).printStackTrace(new PrintWriter(stringWriter));
            LOGGER.error(stringWriter.toString());
        }
    }
}
