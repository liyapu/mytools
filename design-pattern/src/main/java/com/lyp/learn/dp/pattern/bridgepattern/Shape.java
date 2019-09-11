package com.lyp.learn.dp.pattern.bridgepattern;

/**
 * 图形抽象类
 */
public abstract class Shape {
     Color color;

    public Shape(Color color){
        this.color = color;
    }

    /**
     * 抽象方法: 画
     */
    public abstract void draw();
}
