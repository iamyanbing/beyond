package com.beyond.util.ioutil;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * base64编码、解码
 * eg：把图片经base64加密；解密 经base64加密后的文件
 */
@Slf4j
public class JDKBase64 {

    /**
     * Base64
     * JDK 1.8出现的类，所以需要在JDK1.8及以上版本使用
     *
     * 优势：执行效率高，比BASE64Encoder要快至少11倍
     *
     * @param filePath :本地保存的图片路径
     */
    public static void useBase64(String filePath) {
        byte[] data = null;
        // 读取图片字节数组
        try(InputStream in = new FileInputStream(filePath)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            log.error("把图片转成base64失败,文件路径:{}, 异常信息:{}", filePath, e.getMessage());
            //抛出自定义异常
        }
        // 编码（加密）
        String encode = Base64.getEncoder().encodeToString(data);
        log.info(encode);

        // 解码（解密）
        byte[] bytes = Base64.getDecoder().decode(encode);
        String res = new String(bytes);
        log.info(res);

    }

    /**
     * BASE64Encoder
     * JDK 1.8以下版本使用（不包括JDK1.8），且JDK9之后已移除
     *
     * 问题：
     * 根据RFC822规定，base64编码后，每行不能超过76字符，超过则添加回车换行符
     * BASE64Encoder严格准守这一规定，所以需要去除回车换行符，才能给其他服务或者前端用
     *
     * Base64没有准守这一规定。
     *
     * @param filePath :本地保存的图片路径
     */
    public static void useBASE64Encoder(String filePath) throws IOException {
        byte[] data = null;
        // 读取图片字节数组
        try(InputStream in = new FileInputStream(filePath)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            log.error("把图片转成base64失败,文件路径:{}, 异常信息:{}", filePath, e.getMessage());
            //抛出自定义异常
        }
        BASE64Encoder encoder = new BASE64Encoder();
        // 编码（加密）
        String encode = encoder.encode(data).replaceAll("\n", "").replaceAll("\r", "");
        log.info(encode);


        // 解码（解密）
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(encode);
        String res = new String(bytes);
        log.info(res);
    }

}
