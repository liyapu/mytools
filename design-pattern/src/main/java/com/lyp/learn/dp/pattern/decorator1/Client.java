package com.lyp.learn.dp.pattern.decorator1;

/**
 * @author liyapu
 * @date 2021-08-05 15:14
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("----------Circle with normal border----------");
        Shape circle = new Circle();
        circle.draw();

        System.out.println("\n----------Circle of red border----------");
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        redCircle.draw();

        System.out.println("\n----------Rectangle of red border----------");
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        redRectangle.draw();
    }
}
