package com.lyp.learn.dp.pattern.bridgepattern;

public class Client {
    public static void main(String[] args) {
        Color redColor = new Red();
        Color greenColor = new Green();

        Shape circleShape1 = new Circle(redColor);
        Shape circleShape2 = new Circle(greenColor);

        circleShape1.draw();
        circleShape2.draw();

        System.out.println();

        Shape squareShare1 = new Square(redColor);
        Shape squareShare2 = new Square(greenColor);

        squareShare1.draw();
        squareShare2.draw();

    }
}
