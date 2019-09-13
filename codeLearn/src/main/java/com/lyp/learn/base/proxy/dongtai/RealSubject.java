package com.lyp.learn.base.proxy.dongtai;

public class RealSubject implements Subject {
    @Override
    public void printMessage() {
        System.out.println("real subject print message");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("real subject say Hello :" + name);
    }
}
