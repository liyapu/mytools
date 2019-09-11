package com.lyp.learn.dp.pattern.factory2;

public class AddFactory implements Factory {
    @Override
    public Operation CreateOperation() {
        System.out.println("加法运算");
        return new Add();
    }
}
