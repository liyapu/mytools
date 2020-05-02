package com.lyp.learn.pk3;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-02 20:21
 */
public class ExceptionTest {

    public static void main(String[] args) {
        new ExceptionTest().methodA();
    }

    public void methodA(){

        int a = 10;
        try {
            methodB();
            String s = "hello";
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("methodA ....");

    }

    public void methodB(){
        int i = 10;
        int j = 0;
        int k = i /j;
        System.out.println(k);
    }
}
