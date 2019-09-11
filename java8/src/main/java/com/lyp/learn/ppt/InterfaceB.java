package com.lyp.learn.ppt;

public interface InterfaceB {

    default void eat(){
        System.out.println("interface B : eat");
    }
}
