package com.lyp.learn.test;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-23 10:17
 */
public class FinalTest {
    public static void main(String[] args) {
        new FinalTest().one();
        System.out.println("main mmmmmmmmmmm");
    }

    public void one(){
        System.out.println("aaaaaaaaaaaaaaaa");
        try {
            new FinalTest().chu();
        } catch (Exception e) {
            System.out.println("exception e");
            System.out.println("-----------");
            e.printStackTrace();
            throw  e;
        } finally {
            System.out.println("final final ");
        }
        System.out.println("fffffffffffffff");
    }


    public void chu(){
        int a = 10;
        int b = 0;
        int c = a / b;
        System.out.println(c);
    }
}
