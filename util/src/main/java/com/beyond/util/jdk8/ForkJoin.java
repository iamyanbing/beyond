package com.beyond.util.jdk8;

import com.beyond.util.http.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.LongStream;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 22:02
 * <p>
 * 并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。
 * <p>
 * Java 8 中将并行进行了优化，我们可以很容易的对数据进行并行操作。
 * Stream API 可以声明性地通过 parallel() 与sequential() 在并行流与顺序流之间进行切换。
 * <p>
 * Fork/Join 框架：就是在必要的情况下，将一个大任务，进行拆分(fork)成若干个
 * 小任务（拆到不可再拆时），再将一个个的小任务运算的结果进行 join 汇总.
 */
public class ForkJoin {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoin.class);

    public static void exe() {
        long start = System.currentTimeMillis();

        Long sum = LongStream.rangeClosed(0L, 10000000000L)
                .parallel()//底层用fork/join
                .sum();

        LOGGER.info("" + sum);

        long end = System.currentTimeMillis();

        LOGGER.info("耗费的时间为: " + (end - start));
    }
}
