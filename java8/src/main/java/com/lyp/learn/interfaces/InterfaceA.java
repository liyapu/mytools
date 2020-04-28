package com.lyp.learn.interfaces;

public interface InterfaceA {

   default void eat(){
       System.out.println("interface A : eat");
   }
}
