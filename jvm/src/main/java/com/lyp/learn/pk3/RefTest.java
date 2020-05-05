package com.lyp.learn.pk3;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-02 23:31
 */
public class RefTest {
    public static void main(String[] args) {
        int i = 1;
        changeI(i);
        System.out.println("i = " + i);

        int j = 2;
        changeInteger(j);
        System.out.println("j = " + j);

        String str = "hello";
        changeStr(str);
        System.out.println("str = " + str);

    }

    public static void changeI(int a){
        a = 10;
    }

    public static void changeInteger(Integer a){
        a = 10;
    }

    public static void changeStr(String s){
        s = "aaaa";
    }
}
