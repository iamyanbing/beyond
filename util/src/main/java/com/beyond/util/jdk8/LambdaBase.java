package com.beyond.util.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Auther: yanbing
 * @Date: 2019/6/2 15:01
 *
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 * 						    箭头操作符将 Lambda 表达式拆分成两部分：
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * 语法格式一：无参数，无返回值
 * 		() -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，并且无返回值
 * 		(x) -> System.out.println(x)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 		x -> System.out.println(x)
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("函数式接口");
 *			return Integer.compare(x, y);
 *		};
 *
 * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 * 上述 Lambda 表达式中的参数类型都是由编译器推断得出的。Lambda 表达式中无需指定类型，程序依然可以编译，
 * 这是因为 javac 根据程序的上下文，在后台推断出了参数的类型。Lambda 表达式的类型依赖于上下文环境，是由编译器推断出来的。这就是所谓的类型推断”
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口（JDK8接口中的静态方法和默认方法，都不算是抽象方法。）。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 *
 * 	@FunctionalInterface
 * 该注解不是必须的，如果一个接口符合”函数式接口”定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。
 *   如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。
 * Java 8为函数式接口引入了一个新注解@FunctionalInterface，主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式接口定义的时候，编译器会报错。
 * 加不加@FunctionalInterface对于接口是不是函数式接口没有影响，该注解只是提醒编译器去检查该接口是否仅包含一个抽象方法
 */
public class LambdaBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoin.class);
    public static void exe(){
        //语法格式一：无参数，无返回值
        int num = 0;//jdk 1.7 前，前面必须加final。JDK1.8之后系统会默认加
        Runnable runnableOriginal = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        runnableOriginal.run();
        LOGGER.info("-------------------------------");
        Runnable runnableLambda = () -> System.out.println("Hello Lambda!");
        runnableLambda.run();

        //语法格式三：若只有一个参数，小括号可以省略不写
        Consumer<String> con = x -> System.out.println(x);
        con.accept("有参数，无返回值");

        //语法格式二：有参数，有返回值
        //语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
        Comparator<Integer> com4 = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };

        //语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
        Comparator<Integer> com5 = (x, y) -> Integer.compare(x, y);

        //需求：对一个数进行运算
        System.out.println(operation(100, (x) -> x * x));
        System.out.println(operation(200, (y) -> y + 200));
    }

    public static Integer operation(Integer num, Function<Integer,Integer> function){
        return function.apply(num);
    }
}
