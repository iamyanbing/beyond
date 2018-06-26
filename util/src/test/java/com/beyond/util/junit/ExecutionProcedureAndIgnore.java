package com.beyond.util.junit;

import org.junit.*;

/**
 * @Auther: yanbing
 * @Date: 2018/6/26 21:38
 */
public class ExecutionProcedureAndIgnore {
    //execute only once, in the starting
    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    //execute only once, in the end
    @AfterClass
    public static void  afterClass() {
        System.out.println("in after class");
    }

    //execute for each test, before executing test
    @Before
    public void before() {
        System.out.println("in before");
    }

    //execute for each test, after executing test
    @After
    public void after() {
        System.out.println("in after");
    }

    //test case 1
    @Test
    public void testCase1() {
        System.out.println("in test case 1");
    }

    //test case 2
    @Test
    public void testCase2() {
        System.out.println("in test case 2");
    }

    //test case Ignore
    @Ignore
    @Test
    public void testIgnore() {
        System.out.println("in test Ignore");
    }

}
