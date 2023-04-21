package com.lyp.learn.dp.pattern.chainpattern1;

/**
 * 责任链模式
 * 定义接口Filter,具体的过滤规则需要实现这个接口
 *
 * 职责链模式的原理和实现
 * 职责链模式的英文翻译是Chain Of Responsibility Design Pattern。在GoF的《设计模式》中，它是这么定义的：
 * Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chain the receiving objects and pass the request along the chain until an object handles it.
 * 翻译成中文就是：将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。将这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止。
 */
public interface Filter {

    void doFilter(Request request, Response response, FilterChain chain);

}
