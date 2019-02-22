package com.beyond.util.webservice.client;

/**
 * @Auther: yanbing
 * @Date: 2019/1/9 17:57
 */
public class test {
    public static void main(String[] args) {
        HYBService hybService = new HYBService();
        Hello hello = hybService.getHelloPort();
        HYBMethod hybMethod = new HYBMethod();
        hybMethod.setHYBRequest("world!");
        HYBMethodResponse hybMethodResponse = hello.hybMethod(hybMethod, "HYB");
        System.out.println(hybMethodResponse.getHYBResponse());
    }
}
