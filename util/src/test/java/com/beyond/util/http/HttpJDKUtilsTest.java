package com.beyond.util.http;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for simple App.
 */
public class HttpJDKUtilsTest {

    @Ignore
    @Test
    public void getUTConvertToCurrentSystemZoneDate() throws Exception {
        String request = "{\"RequestName\":\"EmsKindList\",\"TimeStamp\":1529729987788,\"MD5\":\"cec9bd366a36496e404221be3248947d\"}";
        String url = "https://api.cnexps.com:443/cgi-bin/EmsData.dll?DoApi";
        String result = HttpJDKUtils.exec(request, url);
    }

    @Ignore
    @Test
    public void connectionTimeOut() {
        String request = "{\"RequestName\":\"EmsKindList\",\"TimeStamp\":1529729987788,\"MD5\":\"cec9bd366a36496e404221be3248947d\"}";
        String url = "https://api.cnexps.com:443/cgi-bin/EmsData.dll?DoApi";
        try {
            String result = HttpJDKUtils.connectionTimeOut(request, url, 1, 30 * 1000);
        } catch (Exception e) {
            //StackTraceElement[] stackTrace = newThrowable.getStackTrace();
            //for (int i = 0; i < stackTrace.length; i++) {
            //    System.out.println(stackTrace[i]);
            //}
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(out);
            e.printStackTrace(pout);
            String ret = new String(out.toByteArray());
            System.out.println(e.getMessage());
            System.out.println(ret);
        }
    }

    @Ignore
    @Test
    public void readTimeOut() throws Exception {
        String request = "{\"RequestName\":\"EmsKindList\",\"TimeStamp\":1529729987788,\"MD5\":\"cec9bd366a36496e404221be3248947d\"}";
        String url = "https://api.cnexps.com:443/cgi-bin/EmsData.dll?DoApi";
        String result = HttpJDKUtils.readTimeOut(request, url, 30 * 1000, 1);
    }

    @Ignore
    @Test
    public void unHostTimeOut() throws Exception {
        String request = "{\"RequestName\":\"EmsKindList\",\"TimeStamp\":1529729987788,\"MD5\":\"cec9bd366a36496e404221be3248947d\"}";
        String url = "https://api.cnexps.com:443/cgi-bin/EmsData.dll?DoApi";
        String result = HttpJDKUtils.unknownHost(request, url, 30 * 1000, 30 * 1000);
    }
}
