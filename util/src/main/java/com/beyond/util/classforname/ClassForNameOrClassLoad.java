package com.beyond.util.classforname;

public class ClassForNameOrClassLoad {
    public static void main(String[] args) throws Exception {
//        Class<ClassForNameObject> clazz = (Class<ClassForNameObject>) Class.forName("com.beyond.util.classforname.ClassForNameObject");
//
        Class<ClassForNameObject> clazz = (Class<ClassForNameObject>) Class.forName("com.beyond.util.classforname.ClassForNameObject", false, Thread.currentThread().getContextClassLoader());
        clazz.newInstance();

//        Class<ClassForNameObject> clazz = ClassForNameObject.class;
//        clazz.newInstance();
    }
}
