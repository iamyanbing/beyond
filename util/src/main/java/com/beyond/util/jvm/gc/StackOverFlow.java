package com.beyond.util.jvm.gc;

/**
 * 栈溢出
 */
public class StackOverFlow {
    public static void main(String[] args) {
        m();
    }

    static void m() {
        m();
    }
}
