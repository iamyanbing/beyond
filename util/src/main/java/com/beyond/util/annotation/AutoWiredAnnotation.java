package com.beyond.util.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class AutoWiredAnnotation {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoWiredAnnotation.class);

    /**
     * 测试时需要把UserController.setUserService方法打开
     *
     * @throws Exception
     */
    public static void testMethod() throws Exception {
        UserController userController = new UserController();
        Class clazz = userController.getClass();
        //创建好UserService
        UserService userService = new UserService();
        LOGGER.info(userService + "");
        //获取类中的属性值？
        Field serviceField = clazz.getDeclaredField("userService");
        //在访问的时候如果是私有的访问类型，也可以直接进行访问
        serviceField.setAccessible(true);
        //获取属性的名称
        String name = serviceField.getName();
        LOGGER.info(name);
        name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        LOGGER.info(name);
        String methodName = "set" + name;
        LOGGER.info(methodName);
        //获取方法对象
        Method method = clazz.getMethod(methodName, UserService.class);
        //执行set方法
        method.invoke(userController, userService);
        LOGGER.info(userController.getUserService() + "");
    }

    public static void testAutoWired() {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            //当前属性是否添加了@AutoWired注解
            AutoWired annotation = field.getAnnotation(AutoWired.class);
            if (annotation != null) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                try {
                    Object o = type.newInstance();
                    field.set(userController, o);
                } catch (InstantiationException e) {
                    LOGGER.error(e.getMessage());
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });

        LOGGER.info(userController.getUserService() + "");
    }
}
