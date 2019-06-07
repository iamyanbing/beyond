package com.beyond.util.jdk8;

import com.beyond.util.jdk8.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.beyond.util.jdk8.entity.Employee.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 16:56
 */
public class StreamAPITerminationOperation {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoin.class);
    static List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    /**
     * allMatch——检查是否匹配所有元素
     * 	anyMatch——检查是否至少匹配一个元素
     * 	noneMatch——检查是否没有匹配的元素
     * 	findFirst——返回第一个元素
     * 	findAny——返回当前流中的任意元素
     * 	count——返回流中元素的总个数
     * 	max——返回流中最大值
     * 	min——返回流中最小值
     */
    public static void exeBase(){
        boolean bl = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(bl);

        boolean bl1 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(bl1);

        boolean bl2 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(bl2);


        Optional<Employee> optional = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(optional.get());
        LOGGER.info("--------------------------------");
        Optional<Employee> optional1 = emps.parallelStream()
                .filter((e) -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(optional1.get());

        long count = emps.stream()
                .filter((e) -> e.getStatus().equals(Status.FREE))
                .count();
        System.out.println(count);

        Optional<Double> optional2 = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(optional2.get());

        Optional<Employee> optional3 = emps.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optional3.get());


        //注意：流进行了终止操作后，不能再次使用
        Stream<Employee> stream = emps.stream()
                .filter((e) -> e.getStatus().equals(Status.FREE));
        long count1 = stream.count();
        stream.map(Employee::getSalary)
                .max(Double::compare);
    }

    /**
     *归约
     * 	reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     *
     * 	map 和 reduce 的连接通常称为 map-reduce 模式，因 Google 用它来进行网络搜索而出名。
     */
    public static void exeReduce(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //第一个参数为起始值，把起始值0作为第一个参数x，从集合中取第一个元素作为y
        //反复结合起来，类似求和
        Integer sum = list.stream()
                .reduce(100, (x, y) -> x + y);
        System.out.println(sum);

        LOGGER.info("----------------------------------------");

        //为什么返回Optional；因为可能为null，上面的方法指定了一个值，所以不可能为null
        //可能为null的值会封装到Optional中
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());

        LOGGER.info("----------------------------------------");

        //需求：搜索名字中 “六” 出现的次数
        Optional<Integer> optional = emps.stream()
                .map(Employee::getName)
                .flatMap(StreamAPIMiddleOperation::filterCharacter)
                .map((ch) -> {
                    if(ch.equals('六'))
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);

        System.out.println(optional.get());
    }

    /**
     *收集
     * 	collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     *
     * 	Collector 接口中方法的实现决定了如何对流执行收集操作(如收
     * 集到 List、Set、Map)。但是 Collectors 实用类提供了很多静态
     * 方法，可以方便地创建常见收集器实例，
     */
    public static void exeCollect(){
        //把流中元素收集到List
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        LOGGER.info("----------------------------------");

        //把流中元素收集到Set
        Set<String> toSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        toSet.forEach(System.out::println);

        LOGGER.info("----------------------------------");

        //把流中元素收集到创建的集合
        HashSet<String> toCollection = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        toCollection.forEach(System.out::println);

        //根据比较器选择最大值
        Optional<Double> maxBy = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println(maxBy.get());

        //根据比较器选择最小值
        Optional<Employee> minBy = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(minBy.get());

        //对流中元素的整数属性求和
        Double summingDouble = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(summingDouble);

        //计算流中元素属性的平均值
        Double averagingDouble = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(averagingDouble);

        //计算流中元素的个数
        Long counting = emps.stream()
                .collect(Collectors.counting());
        System.out.println(counting);

        LOGGER.info("--------------------------------------------");

        //收集流中属性的统计值。
        DoubleSummaryStatistics summarizingDouble = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(summarizingDouble.getMax());
        System.out.println(summarizingDouble.getAverage());
        System.out.println(summarizingDouble.getCount());
        System.out.println(summarizingDouble.getMin());
        System.out.println(summarizingDouble.getSum());

        LOGGER.info("--------------------------------------------");

        //分组:根据某属性值对流分组，属性为K，结果为V
        Map<Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        LOGGER.info("--------------------------------------------");

        //多级分组;先按状态分，再按年龄段分
        Map<Status, Map<String, List<Employee>>> groupingBy = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "老年";
                    else if(e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));
        System.out.println(groupingBy);

        LOGGER.info("--------------------------------------------");

        //分区。满足条件一个区，不满足条件另外一个区
        Map<Boolean, List<Employee>> partitioningBy = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));
        System.out.println(partitioningBy);

        LOGGER.info("--------------------------------------------");

        //连接流中每个字符串
        String joining = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("," , "----", "----"));
        LOGGER.info(joining);

        LOGGER.info("--------------------------------------------");

        //从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值
        Optional<Double> reducing = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(reducing.get());
    }
}
