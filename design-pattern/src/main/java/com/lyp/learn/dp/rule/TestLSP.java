package com.lyp.learn.dp.rule;

/**
 * 动物抽象类
 */
abstract class Animal{

    public abstract void move();

}


class Horse extends Animal{

    @Override
    public void move() {
        System.out.println("马儿跑......");
    }
}

class Bird extends Animal{

    @Override
    public void move() {
        System.out.println("鸟儿飞......");
    }
}

public class TestLSP {
    public static void main(String[] args) {
        Animal animal = null;

        animal = new Horse();
        animal.move();

        animal = new Bird();
        animal.move();

    }
}
