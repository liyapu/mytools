package com.lyp.learn.base.generic.classs;

public class GenericTest2 {
    public static void main(String[] args) {
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);

        System.out.println("泛型测试 key is " + generic.getKey());
        System.out.println("泛型测试 key is " + generic1.getKey());
        System.out.println("泛型测试 key is " + generic2.getKey());
        System.out.println("泛型测试 key is " + generic3.getKey());

        System.out.println();

        System.out.println("泛型测试 key is " + generic.getKey().getClass());
        System.out.println("泛型测试 key is " + generic1.getKey().getClass());
        System.out.println("泛型测试 key is " + generic2.getKey().getClass());
        System.out.println("泛型测试 key is " + generic3.getKey().getClass());
    }
}
