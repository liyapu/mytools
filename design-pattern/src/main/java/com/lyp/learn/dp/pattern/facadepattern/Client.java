package com.lyp.learn.dp.pattern.facadepattern;

/**
 *
 */
public class Client {

    public static void main(String[] args) {
        //秘书类
        Secretary secretary = new Secretary();

        System.out.println("老板告诉秘书要到上海出差10天");
        secretary.trip("北京","上海",10);
        System.out.println("--------------");

        System.out.println("老板告诉秘书要请8个人呢吃饭");
        secretary.repast(8);
    }
}
