package com.beyond.util.junit;

import org.junit.Test;

/**
 * @Auther: yanbing
 * @Date: 2018/6/26 22:24
 */
public class TimeoutAndException {
    /**
     * Junit 提供了一个暂停的方便选项。如果一个测试用例比起指定的毫秒数花费了更多的时间，
     * 那么 Junit 将自动将它标记为失败。timeout 参数和 @Test 注释一起使用。现在让我们看看活动中的 @test(timeout)。
     *
     * @throws Exception
     */
    @Test(timeout = 10)
    public void testTimeout() throws Exception {
        Thread.sleep(100);
    }

    /**
     * Junit 用代码处理提供了一个追踪异常的选项。你可以测试代码是否它抛出了想要得到的异常。
     * expected 参数和 @Test 注释一起使用。现在让我们看看活动中的 @Test(expected)。
     *
     * @throws Exception
     */
    @Test(expected = ClassNotFoundException.class)
    public void testException() throws Exception {
        int a = 0;
        int b = 1 / a;
    }
}
