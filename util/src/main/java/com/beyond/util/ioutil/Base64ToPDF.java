package com.beyond.util.ioutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Base64;

/**
 * @Auther: yanbing
 * @Date: 2019/1/30 14:47
 */
public class Base64ToPDF {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64ToPDF.class);

    public static void exe(String path, String base64sString) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            // 将base64编码的字符串解码成字节数组
            byte[] bytes = decoder.decode(base64sString);
            // apache API
            // byte[] bytes = Base64.decodeBase64(base64sString);
            // 创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            // 创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            // 指定输出的文件。文件后缀为pdf则转换为PDF文件，文件后缀为jpg则转换为图片格式文件。
            //具体转换为PDF还是图片，需要根据base64sString字符串去定义
            //File file = new File("/Users/hyb/source/java/test1.pdf");
            File file = new File(path);
            // 创建到指定文件的输出流
            fout = new FileOutputStream(file);
            // 为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);

            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
                if (fout != null) {
                    fout.close();
                }
                if (bout != null) {
                    bout.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
