package com.beyond.util.jdk8.interfacenewfeature;

/**
 * @Auther: yanbing
 * @Date: 2019/6/7 9:54
 *
 * 选择父类中的方法
 *
 * 重写后调用时用自己类中的方法（getName()），不重写调用时用父类中的方法，类优先原则
 * 如果一个父类提供了具体的实现，那么所有父接口中具有相同名称和参数的默认方法会被忽略。
 *
 * 接口的static方法可以通过接口名直接调用；default方法不能通过接口名直接调用，需要创建对象才能调用
 */
public class IntefaceDemo extends MyYanBing implements MyIYB, MyYB{
//    @Override
//    public String getName() {
//        return "IntefaceDemo";
//    }
}
