package com.lyp.learn.dp.pattern.builderpattern1;

public class ConcreteBuilder extends Builder {
    Product product = new Product();

    @Override
    public void setPartA() {
        product.setPartA("AAAAA");
    }

    @Override
    public void setPartB() {
        product.setPartB("BBBBB");
    }

    @Override
    public void setPartC() {
        product.setPartC("CCCCC");
    }

    @Override
    public Product buildProduct() {
        return product;
    }
}
