package com.lyp.learn.dp.pattern.bridgepattern1;

public class Circle extends Shape {

    public Circle(Color color){
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("画圆形 : 使用颜色 : " + color.getColor());
    }
}
