package com.lyp.mt;

import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:18
 */
public class FirstTest {

    @Test
    public void testEqual(){
//        AssertEquals.assertEquals(2, 1+1);
    }

    @Test
    public void testintByte(){
        int a = 10;
        byte b = 10;
        System.out.println(a == b);
    }

    @Test
    public void testintInteger(){
        int a = 6666;
        Integer b = 6666;
        System.out.println(a == b);
    }
}
