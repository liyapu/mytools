package com.lyp.learn.dp.pattern.builderpattern1;

public class Director {
    private Builder builder = new ConcreteBuilder();

    /**
     * 构造产品，负责调用各个零件建造方法
     */
    public Product build(){
        builder.setPartA();
        builder.setPartB();
        builder.setPartC();
        return builder.buildProduct();
    }
}
