package com.lyp.learn.base.generic.methodd;

public class GenericTest1 {
    public <T> void printMsg( T... args){
        for(T t : args){
            System.out.println("泛型测试 t is " + t);
            System.out.println(t.getClass());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new GenericTest1().printMsg("111",222,"aaaa","2323.4",55.55);

    }

}

