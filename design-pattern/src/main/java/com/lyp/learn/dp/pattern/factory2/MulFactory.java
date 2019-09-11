package com.lyp.learn.dp.pattern.factory2;

public class MulFactory implements Factory {
    @Override
    public Operation CreateOperation() {
        System.out.println("乘法运算");
        return new Mul();
    }
}
