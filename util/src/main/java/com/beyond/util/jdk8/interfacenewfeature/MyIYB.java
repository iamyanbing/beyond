package com.beyond.util.jdk8.interfacenewfeature;

public interface MyIYB {
	
	default String getName(){
		return "iyb";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}

}
