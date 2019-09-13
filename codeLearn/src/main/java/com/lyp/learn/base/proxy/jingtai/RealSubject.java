package com.lyp.learn.base.proxy.jingtai;

public class RealSubject implements Subject {
    @Override
    public void printMessage() {
        System.out.println("real subject print message");
    }
}
