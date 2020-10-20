package com.beyond.util.jvm.classloader;

/**
 * 加载器加载class
 *
 * 测试与 Class.forName()的区别
 */
public class LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = LoadClassByHand.class.getClassLoader().loadClass("com.beyond.util.jvm.classloader.ClassForName");
        System.out.println(clazz.getName());
        ClassForName classForName = (ClassForName) clazz.newInstance();
        ClassForName classForName1 = (ClassForName) clazz.newInstance();//只会执行一次类加载阶段的初始化

//        Class.forName("com.beyond.util.jvm.classloader.ClassForName");
        //利用类加载器加载资源，图片、properties文件等
//        LoadClassByHand.class.getClassLoader().getResourceAsStream("");
    }
}
