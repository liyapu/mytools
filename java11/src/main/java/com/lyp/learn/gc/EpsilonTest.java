package com.lyp.learn.gc;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-28 17:32
 */
public class EpsilonTest {
}


class Garbage{
    private double d1 = 1L;
    private double d2 = 2L;

    //这个方法时 GC 在清除本对象时，会调用一次
    @Override
    public void finalize(){
        System.out.println();
    }
}
