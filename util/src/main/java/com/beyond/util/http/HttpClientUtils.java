package com.beyond.util.http;

import com.beyond.util.character.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Http Client工具包调用代码示例
 *
 * @Auther: yanbing
 * @Date: 2018/6/24 11:07
 */
public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    private static final int MAX_TIMEOUT = 7000;

    /**
     * <pre>响应异常：通过前几条异常信息判断
     * java.net.SocketTimeoutException: Read timed out
     * at java.net.SocketInputStream.socketRead0(Native Method)
     * at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
     * at java.net.SocketInputStream.read(SocketInputStream.java:170)
     * at java.net.SocketInputStream.read(SocketInputStream.java:141)
     * </pre>
     *
     * @param apiUrl
     * @param json
     *
     * @return
     *
     * @throws Exception
     */
    public static String socketTimeout(String apiUrl, String json) throws Exception {
        return doPostSSL(apiUrl, json, MAX_TIMEOUT, 1);
    }

    /**
     * <pre>
     *     连接超时异常；通过前几条异常信息和Caused by: java.net.SocketTimeoutException可以判断
     * org.apache.http.conn.ConnectTimeoutException: Connect to api.cnexps.com:443 [api.cnexps.com/139.198.188.48] timed out
     * at org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:151)
     * at org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect(PoolingHttpClientConnectionManager.java:373)
     * at org.apache.http.impl.execchain.MainClientExec.establishRoute(MainClientExec.java:381)
     * at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:237)
     * at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:185)
     * at org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:89)
     * at org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:111)
     * at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)
     * at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)
     * at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:108)
     * at com.beyond.util.http.HttpClientUtils.doPostSSL(HttpClientUtils.java:98)
     * at com.beyond.util.http.HttpClientUtils.connectTimeout(HttpClientUtils.java:45)
     * at com.beyond.util.http.HttpClientUtilsTest.connectTimeout(HttpClientUtilsTest.java:25)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * at java.lang.reflect.Method.invoke(Method.java:498)
     * at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     * at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     * at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * at com.intellij.junit4.JUnit4TestRunnerUtil$IgnoreIgnoredTestJUnit4ClassRunner.runChild(JUnit4TestRunnerUtil.java:365)
     * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     * at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     * at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     * at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     * at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     * at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     * at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     * at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:117)
     * at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:42)
     * at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:262)
     * at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:84)
     * Caused by: java.net.SocketTimeoutException
     * at java.net.SocksSocketImpl.remainingMillis(SocksSocketImpl.java:111)
     * at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
     * at java.net.Socket.connect(Socket.java:589)
     * at org.apache.http.conn.ssl.SSLConnectionSocketFactory.connectSocket(SSLConnectionSocketFactory.java:339)
     * at org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:142)
     * ... 33 more
     * </pre>
     *
     * @param apiUrl
     * @param json
     *
     * @return
     *
     * @throws Exception
     */
    public static String connectTimeout(String apiUrl, String json) throws Exception {
        return doPostSSL(apiUrl, json, 1, MAX_TIMEOUT);
    }

    /**
     * <pre>
     *     没有连接网络异常：通过前几条异常信息判断
     * java.net.UnknownHostException: api.cnexps.com: nodename nor servname provided, or not known
     * at java.net.Inet6AddressImpl.lookupAllHostAddr(Native Method)
     * at java.net.InetAddress$2.lookupAllHostAddr(InetAddress.java:928)
     * at java.net.InetAddress.getAddressesFromNameService(InetAddress.java:1323)
     * at java.net.InetAddress.getAllByName0(InetAddress.java:1276)
     * at java.net.InetAddress.getAllByName(InetAddress.java:1192)
     * at java.net.InetAddress.getAllByName(InetAddress.java:1126)
     * </pre>
     *
     * @param apiUrl
     * @param json
     *
     * @return
     *
     * @throws Exception
     */
    public static String doPostSSL(String apiUrl, String json) throws Exception {
        return doPostSSL(apiUrl, json, MAX_TIMEOUT, MAX_TIMEOUT);
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     *
     * @return
     */
    public static String doPostSSL(String apiUrl, String json, Integer connectTimeout, Integer socketTimeout) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        CloseableHttpClient httpclient = createAcceptSelfSignedCertificateClient();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(connectTimeout);
        // 设置读取超时
        configBuilder.setSocketTimeout(socketTimeout);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        RequestConfig requestConfig = configBuilder.build();
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            //这一步才连接URL，请求URL
            response = httpclient.execute(httpPost);
            LOGGER.info("ProtocolVersion :" + response.getProtocolVersion());
            LOGGER.info("StatusCode :" + response.getStatusLine().getStatusCode());
            LOGGER.info("ReasonPhrase :" + response.getStatusLine().getReasonPhrase());
            LOGGER.info("StatusLine :" + response.getStatusLine().toString());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        String result = StringUtils.decodeUnicode(httpStr);
        return result;
    }

    private static CloseableHttpClient createAcceptSelfSignedCertificateClient()
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        // use the TrustSelfSignedStrategy to allow Self Signed Certificates

        // we can optionally disable hostname verification.
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory
        return HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();
    }
}
