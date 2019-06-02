package com.beyond.util.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 16:16
 *
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作(终端操作)
 *
 * Java8中有两大最为重要的改变。第一个是 Lambda 表达式；另外一
 * 个则是 Stream API(java.util.stream.*)。
 * Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对
 * 集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。
 * 使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数
 * 据库查询。也可以使用 Stream API 来并行执行操作。简而言之，
 * Stream API 提供了一种高效且易于使用的处理数据的方式。
 *
 * 流(Stream) 到底是什么呢？
 * 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
 * “集合讲的是数据，流讲的是计算！”
 *
 * 注意：
 * ①Stream 自己不会存储元素。
 * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 创建 Stream :一个数据源（如：集合、数组），获取一个流
 */
public class StreamAPICreate {
    public void create(){
        //1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); //获取一个顺序流
        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);

        //4. 创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }
}
