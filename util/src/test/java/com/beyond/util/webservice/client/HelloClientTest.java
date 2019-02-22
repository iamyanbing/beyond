package com.beyond.util.webservice.client;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 16:27
 */
public class HelloClientTest {

    @Ignore
    @Test
    public void testError() throws Exception {
        HYBService hybService = new HYBService();
        Hello hello = hybService.getHelloPort();
        HYBMethod hybMethod = new HYBMethod();
        hybMethod.setHYBRequest("world!");
        HYBMethodResponse hybMethodResponse = hello.hybMethod(hybMethod, "HYB");
        System.out.println(hybMethodResponse.getHYBResponse());
    }
}
