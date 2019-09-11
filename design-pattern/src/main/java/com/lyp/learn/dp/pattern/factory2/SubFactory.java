package com.lyp.learn.dp.pattern.factory2;

public class SubFactory implements Factory {
    @Override
    public Operation CreateOperation() {
        System.out.println("减法运算");
        return new Sub();
    }
}
