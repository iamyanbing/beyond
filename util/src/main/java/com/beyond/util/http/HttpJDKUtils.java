package com.beyond.util.http;

import com.beyond.util.character.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * java JDK调用HTTPS请求代码示例
 *
 * @Auther: yanbing
 * @Date: 2018/6/24 11:07
 */
public class HttpJDKUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpJDKUtils.class);

    /**
     * <pre>
     * Exception(可以通过前几条异常信息判断是否为连接异常)
     * java.net.SocketTimeoutException
     * at java.net.SocksSocketImpl.remainingMillis(SocksSocketImpl.java:111)
     * at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
     * at java.net.Socket.connect(Socket.java:589)
     * </pre>
     *
     * @param request
     * @param url
     * @param connectTimeout
     * @param readTimeout
     *
     * @return
     *
     * @throws Exception
     */
    public static String connectionTimeOut(String request, String url, Integer connectTimeout, Integer readTimeout) throws Exception {
        return exec(request, url, connectTimeout, readTimeout);
    }

    /**
     * <pre>
     * Exception(可以通过前几条异常信息判断是否为响应异常)
     * java.net.SocketTimeoutException: Read timed out
     * at java.net.SocketInputStream.socketRead0(Native Method)
     * at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
     * at java.net.SocketInputStream.read(SocketInputStream.java:170)
     * at java.net.SocketInputStream.read(SocketInputStream.java:141)
     * </pre>
     *
     * @param request
     * @param url
     * @param connectTimeout
     * @param readTimeout
     *
     * @return
     *
     * @throws Exception
     */
    public static String readTimeOut(String request, String url, Integer connectTimeout, Integer readTimeout) throws Exception {
        return exec(request, url, connectTimeout, readTimeout);
    }

    /**
     * <pre>
     *     可以通过前几条异常信息和异常类判断是否为 没有连接网络异常
     * java.net.UnknownHostException: api.cnexps.com
     * at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:184)
     * at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
     * at java.net.Socket.connect(Socket.java:589)
     * at sun.net.NetworkClient.doConnect(NetworkClient.java:175)
     * </pre>
     *
     * @param request
     * @param url
     * @param connectTimeout
     * @param readTimeout
     *
     * @return
     *
     * @throws Exception
     */
    public static String unknownHost(String request, String url, Integer connectTimeout, Integer readTimeout) throws Exception {
        return exec(request, url, connectTimeout, readTimeout);
    }

    public static String exec(String request, String url) throws Exception {
        return exec(request, url, 30 * 1000, 30 * 1000);
    }

    /**
     * <pre>
     * https=http+ssl（tls）
     * https需要证书，可以设置跳过证书验证.
     * https通过TCP连接时需要cipher suite；JDK7所支持的cipher suite和JDK8所支持的cipher suite不一样；可以设置TCP连接是传递的cipher suite。
     * 可以通过curl -v "httpsURL" 查看TCP连接时所需要的cipher suite。
     * https://www.cnblogs.com/cioliuguilan/p/5518798.html SSL证书(HTTPS)背后的加密算法
     * </pre>
     *
     * @param request:请求参数
     * @param url:请求url
     *
     * @return
     *
     * @throws Exception
     */
    public static String exec(String request, String url, Integer connectTimeout, Integer readTimeout) throws Exception {
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url);
        // 打开连接,是否连接网络都可以打开
        HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        //设置加载协议
        //SSLContext sc = SSLContext.getInstance("TLSv1.2");
        //sc.init(null, trustAllCerts, new SecureRandom());
        //connection.setSSLSocketFactory(sc.getSocketFactory());

        //自定义TCP连接时传递的cipher suite。TLSSocketConnectionFactory : 172
        //连接网络与否都可以执行这步
        connection.setSSLSocketFactory(new TLSSocketConnectionFactory());

        connection.setDefaultHostnameVerifier(
                new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        return true;
                    }
                }
        );

        // 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        BufferedReader reader = null;
        PrintWriter out = null;
        try {
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            //真正去连接URL
            connection.connect();

            out = new PrintWriter(connection.getOutputStream());
            out.print(request);
            System.out.println(request);
            out.flush();

            int statusCode = connection.getResponseCode();
            if (statusCode < 200 || statusCode >= 300) {
                throw new RemoteException("HTTP request error. response code: " + statusCode + ", response message: " + connection.getResponseMessage());
            }

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String result = StringUtils.decodeUnicode(stringBuilder.toString());
            LOGGER.info("原始结果（包含Unicode）" + stringBuilder.toString());
            LOGGER.info("转换后结果" + result);
            return result;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭连接
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
