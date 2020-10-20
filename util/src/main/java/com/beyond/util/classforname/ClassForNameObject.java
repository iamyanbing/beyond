package com.beyond.util.classforname;

public class ClassForNameObject {
    static int a = 8;

    static {
        System.out.println(a);
        System.out.println("static");
    }

    public void test(){
        System.out.println("run");
    }
}
