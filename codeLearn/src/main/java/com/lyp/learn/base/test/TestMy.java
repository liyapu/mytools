package com.lyp.learn.base.test;

import org.junit.Test;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-09-28 19:55
 */
public class TestMy {

    @Test
    public void test01(){
        int i = 10;
//        i++;
        i = i++;
        System.out.println(i);
    }
}
