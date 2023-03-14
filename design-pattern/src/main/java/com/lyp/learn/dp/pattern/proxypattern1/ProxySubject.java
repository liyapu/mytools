package com.lyp.learn.dp.pattern.proxypattern1;

public class ProxySubject implements Subject{
    //代理对象，持有真实主题对象
    private Subject subject = null;

    public ProxySubject(Subject subject){
        this.subject = subject;
    }

    @Override
    public void request() {
        this.beforeRequest();
        subject.request();
        this.afterRequest();
    }

    /**
     * 执行之前的准备工作，记日志等
     */
    private void beforeRequest(){
        System.out.println("代理主题 准备工作.....");
    }

    /**
     * 清除垃圾，恢复测试数据等
     */
    private void afterRequest(){
        System.out.println("代理主题 清扫工作....");
    }
}
