package com.beyond.util.jdk8.interfacenewfeature;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class IntefaceDemoTest {

    @Ignore
    @Test
    public void intefaceDemo() throws Exception {
        IntefaceDemo intefaceDemo=new IntefaceDemo();
        System.out.println(intefaceDemo.getName());
        MyIYB.show();
    }

    @Ignore
    @Test
    public void intefaceDemoOnlyInterface() throws Exception {
        IntefaceDemoOnlyInterface intefaceDemo=new IntefaceDemoOnlyInterface();
        System.out.println(intefaceDemo.getName());
    }

}
