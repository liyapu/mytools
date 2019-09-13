package com.lyp.learn.test;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-15 21:51
 */
public class Circle {
    private int x;
    private int y;

    public Circle() {
    }

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


    public void moveCircle(Circle circle, int deltaX, int deltaY) {
        // code to move origin of circle to x+deltaX, y+deltaY
        circle.setX(circle.getX() + deltaX);
        circle.setY(circle.getY() + deltaY);

        // code to assign a new reference to circle
        circle = new Circle(0, 0);
    }

    public static void main(String[] args) {
        Circle myCircle = new Circle(10,20);
        System.out.println(myCircle);
        new Circle().moveCircle(myCircle, 40, 56);
        System.out.println(myCircle);

    }
}
