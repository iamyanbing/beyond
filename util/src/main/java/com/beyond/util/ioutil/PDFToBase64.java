package com.beyond.util.ioutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Base64;

/**
 * @Auther: yanbing
 * @Date: 2019/1/30 14:56
 */
public class PDFToBase64 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFToBase64.class);

    public static String exe(File file) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        try {
            // 建立读取文件的文件输出流
            fin = new FileInputStream(file);
            // 在文件输出流上安装节点流（更大效率读取）
            bin = new BufferedInputStream(fin);
            // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            baos = new ByteArrayOutputStream();
            // 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            // sun API
            return encoder.encodeToString(bytes).trim();
            // apache API
            // return Base64.encodeBase64String(bytes);

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                fin.close();
                bin.close();
                // 关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException
                // IOException
                // baos.close();
                bout.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }
}
