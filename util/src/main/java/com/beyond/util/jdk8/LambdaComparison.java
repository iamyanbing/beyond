package com.beyond.util.jdk8;

import com.beyond.util.jdk8.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;

public class LambdaComparison {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoin.class);
    static List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    public static void contrast(){
        //原来的匿名内部类
        Comparator<String> originalCom = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        TreeSet<String> originalTreeSet = new TreeSet<>(originalCom);

        TreeSet<String> originalTreeSet2 = new TreeSet<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }

        });

        //现在的 Lambda 表达式
        Comparator<String> com = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> ts = new TreeSet<>(com);

        List<Employee> list = filterEmployee(emps, (e) -> e.getAge() <= 35);
        list.forEach(System.out::println);

        LOGGER.info("------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, (e) -> e.getSalary() >= 5000);
        list2.forEach(System.out::println);
    }

    //优化方式一：策略设计模式
    private static List<Employee> filterEmployee(List<Employee> emps, Predicate<Employee> mp){
        List<Employee> list = new ArrayList<>();
        for (Employee employee : emps) {
            if(mp.test(employee)){
                list.add(employee);
            }
        }
        return list;
    }
}
