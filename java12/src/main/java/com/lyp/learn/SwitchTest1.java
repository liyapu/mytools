//package com.lyp.learn;
//
//
//import org.junit.jupiter.api.Test;
//
///**
// * @author shkstart
// * @create 2019 下午 3:13
// */
//public class SwitchTest1 {
//    //java 12的新特性
//    @Test
//    public void testSwitch1(){
//        Fruit fruit = Fruit.APPLE;
//        switch(fruit) {
//            case PEAR -> System.out.println("4");
//            // 箭头符号 不需要写 break了，多个以逗号分隔
//            case APPLE,GRAPE,MANGO -> System.out.println("5");
//            case ORANGE,PAPAYA -> System.out.println("6");
//            default -> throw new IllegalStateException("No Such Fruit:" + fruit);
//        }
//    }
//
//    @Test
//    public void testSwitch2(){
//        int numberOfLetters;
//        Fruit fruit = Fruit.APPLE;
//
//        //可以有返回值了
//        numberOfLetters = switch(fruit) {
//            case PEAR -> 4;
//            case APPLE,GRAPE,MANGO -> 5;
//            case ORANGE,PAPAYA -> 6;
//            default -> throw new IllegalStateException("No Such Fruit:" + fruit);
//        };
//        System.out.println(numberOfLetters);
//
//    }
//}
