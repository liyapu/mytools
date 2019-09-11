package com.lyp.learn.dp.pattern.proxypattern;

public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        ProxySubject proxySubject = new ProxySubject(subject);
        proxySubject.request();
    }
}
