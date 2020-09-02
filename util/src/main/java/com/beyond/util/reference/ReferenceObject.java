package com.beyond.util.reference;

public class ReferenceObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }

    public void print(){
        System.out.println("print");
    }
}
