package com.lyp.learn.base.generic.classs;

public class GenericTest4 {
    public static void main(String[] args) {
        Generic<Number> gNumber = new Generic<Number>(456);
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<String> gString = new Generic<>("aaa");

        showKeyValue(gNumber);
        showKeyValue(gInteger);
        showKeyValue(gString);
    }

    //Number 换成了 ？
    public static void showKeyValue(Generic<?> obj){
        System.out.println("泛型测试 key value is " + obj.getKey());
    }
}
