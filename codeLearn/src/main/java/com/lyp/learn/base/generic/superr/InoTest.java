package com.lyp.learn.base.generic.superr;



public class InoTest {
    public static void main(String args[]){
        Info<String> i1 = new Info<String>() ;      // 声明String的泛型对象
        Info<Object> i2 = new Info<Object>() ;      // 声明Object的泛型对象
        Info<Integer> i3 = new Info<>();           // 声明Integer的泛型对象

        i1.setVar("hello") ;
        i2.setVar(new Object()) ;
        i3.setVar(222);

        fun(i1) ;
        fun(i2) ;
        //fun(Info<? super java.lang.String>) cannot be applied to (Info<java.lang.Integer>)
       // fun(i3);
    }
    public static void fun(Info<? super String> temp){    // 只能接收String或Object类型的泛型
        System.out.println(temp) ;
    }
}
