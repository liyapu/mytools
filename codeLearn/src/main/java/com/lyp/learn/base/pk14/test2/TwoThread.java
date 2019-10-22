package com.lyp.learn.base.pk14.test2;

import org.junit.Test;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-06-10 14:30
 */
public class TwoThread {
    public int start = 1;

    public  boolean flag = false;


    public static void main(String[] args) {
        TwoThread twoThread = new TwoThread();
        Thread a = new Thread(new JiNum(twoThread));
        a.setName("a");

        Thread b = new Thread(new OuNum(twoThread));
        b.setName("b");

        a.start();
        b.start();
    }

    @Test
    public void test1(){
        System.out.println("aaa");
    }
}
