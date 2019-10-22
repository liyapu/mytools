package com.lyp.learn.base.pk04;

public class Fruit {

    public String color;
    public int weight;

    /**
     * 无参构造方法
     */
    public Fruit(){

    }

    /**
     * 有参构造方法
     * @param color
     * @param weight
     */
    public Fruit(String color,int weight){
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getInfo(){
        return "水果 : " + weight + ":" + color;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public static void main(String[] args) {
        //调用无参构造方法
        Fruit f1 = new Fruit();
        System.out.println(f1.color);
        System.out.println(f1.weight);
        System.out.println(f1);
        System.out.println();

        //调用有参构造方法
        Fruit f2 = new Fruit("red",100);
        System.out.println(f2.color);
        System.out.println(f2.weight);
        System.out.println(f2);
    }
}
