package com.lyp.learn.base.proxy.jingtai;

public class ProxySubject implements Subject {
    // 真实的主题对象
    private Subject target;

    public  ProxySubject(Subject target){
        this.target = target;
    }

    @Override
    public void printMessage() {
        before();
        target.printMessage();
        after();
    }

    public void before(){
        System.out.println("proxy subject 准备工作");
    }

    public void after(){
        System.out.println("proxy subject 清理工作");
    }
}
