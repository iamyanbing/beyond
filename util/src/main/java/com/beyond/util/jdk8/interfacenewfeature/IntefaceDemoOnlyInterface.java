package com.beyond.util.jdk8.interfacenewfeature;

/**
 * @Auther: yanbing
 * @Date: 2019/6/7 9:54
 *
 * 接口冲突
 *
 * 如果一个父接口提供一个默认方法，而另一个接
 * 口也提供了一个具有相同名称和参数列表的方法（不管方法
 * 是否是默认方法），那么子类必须覆盖该方法来解决冲突
 */
public class IntefaceDemoOnlyInterface implements MyIYB, MyYB{
    @Override
    public String getName() {
        return MyIYB.super.getName();
    }

}
