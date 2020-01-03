package com.lyp.mt;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

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

    @Test
    public void testBigDecimal(){
        BigDecimal bd1 = new BigDecimal(0.1);
        System.out.println(bd1);
        BigDecimal bd2 = new BigDecimal("0.1");
        System.out.println(bd2);
    }

    @Test
    public void testIdNumber(){
        String idNum = "530111197308175070";
    }

    @Test
    public void testDot(){
        String pk = "com.lyp.learn";
        String newPk = pk.replace(".", File.separator);
        System.out.println(newPk);
    }
}
