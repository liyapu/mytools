package com.lyp.learn.base.proxy.dongtai2;

public class RealSubject implements Subject {
    @Override
    public void printMessage() {
        System.out.println("real subject print message");
    }

    @Override
    public Result sayHello(String name) {
        System.out.println("real subject say Hello :" + name);
        return new Result(200,"问候成功");
    }
}
