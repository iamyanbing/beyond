package com.beyond.util.http;

import org.junit.Ignore;
import org.junit.Test;

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
}
