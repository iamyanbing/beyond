package com.beyond.util.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 15:27
 * 什么是函数式接口
 * 只包含一个抽象方法的接口，称为函数式接口。
 * 你可以通过 Lambda 表达式来创建该接口的对象。（若 Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明）。
 * 我们可以在任意函数式接口上使用 @FunctionalInterface 注解，这样做可以检查它是否是一个函数式接口，同时 javadoc 也会包含一条声明，说明这个接口是一个函数式接口。
 *
 * Java8 内置的四大核心函数式接口 (使用最多的四个函数式接口)
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class LambdaFunctionalInterface {
    public static void exe(){
        //作为参数传递 Lambda 表达式：为了将 Lambda 表达式作为参数传递，接
        //收Lambda 表达式的参数类型必须是与该 Lambda 表达式兼容的函数式接口
        //的类型。

        System.out.println("Consumer<T> 消费型接口 :");
        consumer(10000, (m) -> System.out.println("Consumer<T> :" + m));

        System.out.println("Supplier<T> 供给型接口 :");
        List<Integer> numList = supplier(10, () -> (int)(Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }

        System.out.println("Function<T, R> 函数型接口：");
        String newStr = function("yan", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = function("bing", (str) -> str.substring(1, 2));
        System.out.println(subStr);

        System.out.println("Predicate<T> 断言型接口：");
        List<String> list = Arrays.asList("Hello", "yanbing", "Lambda", "www", "com");
        List<String> strList = predicate(list, (s) -> s.length() > 3);
        for (String str : strList) {
            System.out.println(str);
        }
    }

    public static void consumer(double money, Consumer<Double> con){
        con.accept(money);
    }

    //需求：产生指定个数的整数，并放入集合中
    public static List<Integer> supplier(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }

        return list;
    }

    //需求：用于处理字符串
    public static String function(String str, Function<String, String> fun){
        return fun.apply(str);
    }

    //需求：将满足条件的字符串，放入集合中
    public static List<String> predicate(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if(pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }
}
