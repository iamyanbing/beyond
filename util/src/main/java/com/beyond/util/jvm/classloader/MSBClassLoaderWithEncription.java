package com.beyond.util.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 自定义类加载器；
 * class文件内容加解密；可以防止反编译、防止篡改
 */
public class MSBClassLoaderWithEncription extends ClassLoader{
    public static int seed = 0B10110110;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("F:/github/beyond/util/target/classes/", name.replace('.', '/').concat(".msbclass"));

        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;

            while ((b=fis.read()) !=0) {
                baos.write(b ^ seed);
            }

            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();//可以写的更加严谨

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name); //throws ClassNotFoundException
    }

    public static void main(String[] args) throws Exception {

        File f = new File("F:/github/beyond/util/target/classes/", "com.beyond.util.jvm.classloader.HelloWorld".replace('.', '/').concat(".msbclass"));

        encFile("com.beyond.util.jvm.classloader.HelloWorld");

        ClassLoader l = new MSBClassLoaderWithEncription();
        Class clazz = l.loadClass("com.beyond.util.jvm.classloader.HelloWorld");
        HelloWorld h = (HelloWorld)clazz.newInstance();
        h.say();

        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
    }

    private static void encFile(String name) throws Exception {
        File f = new File("F:/github/beyond/util/target/classes/", name.replace('.', '/').concat(".class"));
        FileInputStream fis = new FileInputStream(f);
        FileOutputStream fos = new FileOutputStream(new File("F:/github/beyond/util/target/classes/", name.replace(".", "/").concat(".msbclass")));
        int b = 0;

        while((b = fis.read()) != -1) {
            fos.write(b ^ seed);
        }

        fis.close();
        fos.close();
    }
}
