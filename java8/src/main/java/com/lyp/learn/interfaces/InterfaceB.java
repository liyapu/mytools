package com.lyp.learn.interfaces;

public interface InterfaceB {

    default void eat(){
        System.out.println("interface B : eat");
    }
}
