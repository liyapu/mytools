//package com.lyp.learn;
//
///**
// * @author shkstart
// * @create 2019 下午 3:06
// */
//public class SwitchTest {
//    public static void main(String[] args) {
//        int numberOfLetters;
//        Fruit fruit = Fruit.APPLE;
//        switch (fruit) {
//            case PEAR:
//                numberOfLetters = 4;
//                break;
//            //case 穿透
//            case APPLE:
//            case GRAPE:
//            case MANGO:
//                numberOfLetters = 5;
//            break;
//            case ORANGE:
//            case PAPAYA:
//                numberOfLetters = 6;
//                break;
//            default:
//                throw new IllegalStateException("No Such Fruit:" + fruit);
//        }
//        System.out.println(numberOfLetters);
//
//    }
//}
//
//enum Fruit {
//    PEAR, APPLE, GRAPE, MANGO, ORANGE, PAPAYA;
//}
