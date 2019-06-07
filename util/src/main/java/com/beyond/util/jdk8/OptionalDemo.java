package com.beyond.util.jdk8;

import com.beyond.util.jdk8.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 22:39
 *
 * Optional<T> 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在，
 * 原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。并且
 * 可以避免空指针异常。
 *
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class OptionalDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoin.class);
    public static void exe(){
        //Optional.of(T t) : 创建一个 Optional 实例
        Optional<Employee> optional = Optional.of(new Employee());
        Employee emp = optional.get();
        System.out.println(emp);

//        Optional<Employee> optional1 = Optional.ofNullable(null);
//		System.out.println(optional1.get());

//		Optional<Employee> optional2 = Optional.empty();
//		System.out.println(optional2.get());

        Optional<Employee> optional1 = Optional.ofNullable(new Employee());

        if(optional1.isPresent()){
            System.out.println(optional1.get());
        }


        Employee employee = optional1.orElse(new Employee("张三"));
        System.out.println(employee);

        Employee employee1 = optional1.orElseGet(() -> new Employee());
        System.out.println(employee1);

        //map
        Optional<Employee> optional2 = Optional.of(new Employee(101, "张三", 18, 9999.99));

        Optional<String> optionalS = optional2.map(Employee::getName);
        LOGGER.info(optionalS.get());

        Optional<String> optionalS1 = optional2.flatMap((e) -> Optional.of(e.getName()));
        LOGGER.info(optionalS1.get());
    }
}
