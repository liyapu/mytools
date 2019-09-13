package com.lyp.learn.base.proxy.jingtai;

public class SubjectRun {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ProxySubject proxySubject = new ProxySubject(realSubject);

        proxySubject.printMessage();
    }
}
