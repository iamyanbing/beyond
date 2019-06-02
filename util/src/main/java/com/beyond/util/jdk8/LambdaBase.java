package com.beyond.util.jdk8;

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
 * 语法格式二：有参数，有返回值
 * 		(x) -> Syst一个参数，并且无em.out.println(x)
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
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 */
public class LambdaBase {

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
        System.out.println("-------------------------------");
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
