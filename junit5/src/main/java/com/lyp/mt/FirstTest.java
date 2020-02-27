package com.lyp.mt;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:18
 */
public class FirstTest {
    @Test
    public void testUUid2(){
//        String uuid1 = "在e65deb4c-a110-49c8-a4ef-6e69447968d6";
//        String uuid1 = "在e65deb4ca11049c8a4ef6e69447968d6";
        String uuid1 = "北京儿童医院——监测平台近期开发计划_c5cf113415be40ab8c7fab3bde192acb.xlsx";
//        String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
//        System.out.println(uuid1.matches(regex));

//        String reg = "[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
        Pattern p= Pattern.compile("_[0-9a-f]{32}\\.");
        Matcher m=p.matcher(uuid1);
        System.out.println(m.find());
    }


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
