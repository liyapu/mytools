package com.lyp.learn.base.demo.pk02;

public class switchOperator {
    public static void main(String[] args) {
        Season season = Season.SUMMER;

        switch (season){
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
            default:
                    System.out.println("default");
        }
    }
}
//定义一个枚举类，四季的表示，春，夏，秋，冬
 enum Season {
    SPRING,SUMMER,AUTUMN,WINTER
}