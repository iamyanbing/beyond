package com.beyond.app.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * 查看系统所支持和激活的SSL、TLS协议版本
 *
 * @Auther: yanbing
 * @Date: 2018/6/24 11:07
 */
public class SSLProtocol {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSLProtocol.class);

    public static void main(String[] args) throws Exception{
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        //SSLContext context = SSLContext.getInstance("SSL");
        context.init(null, null, null);

        SSLSocketFactory factory = context.getSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket();

        String[] protocols = socket.getSupportedProtocols();

        LOGGER.info("Supported Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            LOGGER.info(" " + protocols[i]);
        }
        protocols = socket.getEnabledProtocols();

        LOGGER.info("Enabled Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            LOGGER.info(" " + protocols[i]);
        }
    }

}
