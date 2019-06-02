package com.beyond.util.jdk8;

import com.beyond.util.jdk8.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 16:21
 */
public class StreamAPIMiddleOperation {

    static List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );
    /**
     * 筛选与切片
     * 	filter——接收 Lambda ， 从流中排除某些元素。
     * 	limit——截断流，使其元素不超过给定数量。
     * 	skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * 	distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    public static void exeFilter(){
        //所有的中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("filter");
                    return e.getAge() <= 35;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);
        System.out.println("--------------------");

        //满足条件之后，后面元素不再判断
        emps.stream()
                .filter((e) -> {
                    System.out.println("截断流"); // &&  ||
                    return e.getSalary() >= 7000;
                }).limit(3)
                .forEach(System.out::println);
        System.out.println("--------------------");
        emps.parallelStream()
                .filter((e) -> {
                    System.out.println("skip");
                    return e.getSalary() >= 5000;
                }).skip(2)
                .forEach(System.out::println);
        System.out.println("--------------------");
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * 	map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * 	flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     * 	map和flatMap关系有点类似Collection的add和addAll
     */
    public static void exeMap(){
        //提取信息
        Stream<String> str = emps.stream()
                .map((e) -> e.getName());

        System.out.println("-------------------------------------------");

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        //将元素转换成其他形式
        Stream<String> stream = strList.stream()
                .map(String::toUpperCase);

        stream.forEach(System.out::println);

        //流中存放流，所以在下面遍历的时候需要嵌套遍历
        //把lambda中返回的流直接加到要返回的流中
        Stream<Stream<Character>> stream2 = strList.stream()
                .map(StreamAPIMiddleOperation::filterCharacter);
        System.out.println("遍历对象-------------------------------------------");
        stream2.forEach(System.out::println);

        System.out.println("遍历元素-------------------------------------------");
        Stream<Stream<Character>> streamStream = strList.stream()
                .map(StreamAPIMiddleOperation::filterCharacter);
        streamStream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------------------");
        //把lambda中返回的流 中的元素取出来，然后把取出的元素加到要返回的流中
        Stream<Character> stream3 = strList.stream()
                .flatMap(StreamAPIMiddleOperation::filterCharacter);

        stream3.forEach(System.out::println);
    }

    /**
     *  sorted()——自然排序  Comparable  compareTo
     * 	sorted(Comparator compare)——定制排序 Comparator compare
     */
    public static void exeSorted(){
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        emps.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
}
