package com.lyp.learn.junit;

/**
 * @author liyapu
 * @date 2023-06-29 15:34
 * @description
 */
public class MyMain {

    public static void main(String[] args) {
        Integer a = new Integer(2);
        Integer b = new Integer(2);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println("---------");

        Integer c = 2;
        Integer d = 2;
        System.out.println(c == d);
        System.out.println(c.equals(d));
        System.out.println("---------");

        int e = 2;
        int f = 2;
        System.out.println(e == f);
        System.out.println("---------");

    }
}
