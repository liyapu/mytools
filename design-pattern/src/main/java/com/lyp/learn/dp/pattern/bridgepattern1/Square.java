package com.lyp.learn.dp.pattern.bridgepattern1;

public class Square extends Shape {

    public Square(Color color){
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("画方形 : 使用颜色 : " + color.getColor());
    }
}
