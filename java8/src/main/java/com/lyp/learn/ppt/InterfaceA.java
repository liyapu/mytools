package com.lyp.learn.ppt;

public interface InterfaceA {

   default void eat(){
       System.out.println("interface A : eat");
   }
}
