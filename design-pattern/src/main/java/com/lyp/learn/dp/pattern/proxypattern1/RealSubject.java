package com.lyp.learn.dp.pattern.proxypattern1;

public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("真实主题 request方法执行......");
    }
}
