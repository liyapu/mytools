package com.lyp.learn.base.proxy.cglib;

public class MessageRun {
    public static void main(String[] args) {
        Message messageProxyCglib =  new ProxyCglib().createProxy(new Message());
        messageProxyCglib.sendMessage("cglib");
    }
}
