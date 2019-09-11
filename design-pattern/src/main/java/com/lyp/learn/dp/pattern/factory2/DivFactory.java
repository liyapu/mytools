package com.lyp.learn.dp.pattern.factory2;

public class DivFactory implements Factory {
    @Override
    public Operation CreateOperation() {
        System.out.println("除法运算");
        return new Div();
    }
}
