package com.lyp.learn.dp.pattern.proxypattern1;

public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        ProxySubject proxySubject = new ProxySubject(subject);
        proxySubject.request();
    }
}
