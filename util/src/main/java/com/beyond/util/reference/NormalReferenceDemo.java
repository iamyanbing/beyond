package com.beyond.util.reference;

import java.io.IOException;

/**
 * strong reference 强引用
 */
public class NormalReferenceDemo {
    public static void main(String[] args) throws IOException {
        ReferenceObject referenceObject = new ReferenceObject();
        referenceObject = null;
        System.gc();//垃圾回收时会打印referenceObject对象finalize()输出语句

        System.in.read();//作用为使程序阻塞，没有任何含义
    }
}
