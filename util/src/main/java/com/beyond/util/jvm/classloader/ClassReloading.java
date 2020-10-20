package com.beyond.util.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 破坏双亲委派机制，重写loadClass()。
 */
public class ClassReloading {
    private static class MyLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {

            File f = new File("F:/github/beyond/util/target/classes/" + name.replace(".", "/").concat(".class"));

            if(!f.exists()) return super.loadClass(name);

            try {

                InputStream is = new FileInputStream(f);

                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws Exception {
        MyLoader m = new MyLoader();
        Class clazz = m.loadClass("com.beyond.util.jvm.classloader.HelloWorld");

        m = new MyLoader();
        Class clazzNew = m.loadClass("com.beyond.util.jvm.classloader.HelloWorld");

        System.out.println(clazz == clazzNew);
    }
}
