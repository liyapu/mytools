package com.lyp.learn.cglib.proxy;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 19:21
 */
public class Sample {

    public void sayHello(){
        System.out.println("hello world");
    }

    public String printMessage(String message){
        return "message is : " + message;
    }
}
