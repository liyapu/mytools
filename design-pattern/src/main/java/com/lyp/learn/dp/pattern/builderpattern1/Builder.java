package com.lyp.learn.dp.pattern.builderpattern1;

public abstract class Builder {
    /**
     * 设置产品的不同部分
     */
    public abstract void  setPartA();

    public abstract void setPartB();

    public abstract void setPartC();

    /**
     * 建造产品
     * @return
     */
    public abstract Product buildProduct();
}
