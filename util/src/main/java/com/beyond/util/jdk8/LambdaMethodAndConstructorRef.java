package com.beyond.util.jdk8;

import com.beyond.util.jdk8.entity.Employee;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 15:45
 *
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 *    lambda体中有返回值，函数式接口中没有返回值，可以；反过来这不可以。
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName(类名 :: 实例方法名)
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 */
public class LambdaMethodAndConstructorRef {
    public static void exeMethodRef(){
        //对象的引用 :: 实例方法名
        PrintStream ps = System.out;
        Consumer<String> con = (str) -> ps.println(str);
        con.accept("Hello World！");
        System.out.println("--------------------------------");
        Consumer<String> con2 = ps::println;
        con2.accept("Hello Java8！");
        Consumer<String> con3 = System.out::println;

        Employee emp = new Employee(101, "张三", 18, 9999.99);
        Supplier<String> sup = () -> emp.getName();
        System.out.println(sup.get());
        System.out.println("----------------------------------");
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());
        // lambda实体中有返回值，接口中没有返回值可，可以；反过来这不可以
        Consumer<String> sup3 = emp::setNameTest;
        sup3.accept("asd");
        System.out.println();

        //类名 :: 静态方法名
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println("-------------------------------------");
        Comparator<Integer> com2 = Integer::compare;

        BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
        System.out.println(fun.apply(1.5, 22.2));
        System.out.println("--------------------------------------------------");
        BiFunction<Double, Double, Double> fun2 = Math::max;
        System.out.println(fun2.apply(1.2, 1.5));

        //类名 :: 实例方法名
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        System.out.println(biPredicate.test("abcde", "abcde"));

        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("abc", "abc"));

        Function<Employee, String> function = (e) -> e.show();
        System.out.println(function.apply(new Employee()));

        Function<Employee, String> function1 = Employee::show;
        System.out.println(function1.apply(new Employee()));
    }

    public static void exeConstructorRef(){
        Function<String, Employee> function = Employee::new;
        BiFunction<String, Integer, Employee> biFunction = Employee::new;

        Supplier<Employee> supplier = () -> new Employee();
        System.out.println(supplier.get());

        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    public static void exeArrayRef(){
        Function<Integer, String[]> fun = (args) -> new String[args];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, Employee[]> fun2 = Employee[] :: new;
        Employee[] emps = fun2.apply(20);
        System.out.println(emps.length);
    }
}
