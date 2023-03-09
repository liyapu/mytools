package com.lyp.learn.dp.pattern.prototype1;

public class Client {

    public static void main(String[] args) {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        Prototype prototype = concretePrototype.clone();
    }
}
