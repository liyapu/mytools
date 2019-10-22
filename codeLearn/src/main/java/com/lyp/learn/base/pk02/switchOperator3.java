package com.lyp.learn.base.pk02;

public class switchOperator3 {
    public static void main(String[] args) {
        String str = "B";

        switch (str){
            case "A":
                System.out.println("AAA");
                break;
            case "B":
                System.out.println("BBB");
                break;
            case "C":
                System.out.println("CCC");
                break;
            case "D":
                System.out.println("DDD");
                break;
            default:
                    System.out.println("default");
        }
    }
}
