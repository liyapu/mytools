package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-20 11:57
 */
public class RandomStringUtilsTest {


    /**
     * 指定位数的随机数字
     */
    @Test
    public void testRandomNumeric(){
        for (int i = 0; i < 100; i++) {
            String num = RandomStringUtils.randomNumeric(5);
            System.out.println(num);
        }
    }

    /**
     * 指定位数的随机数字 [包含,不包含)
     */
    @Test
    public void testRandomNumeric2(){
        for (int i = 0; i < 100; i++) {
            String num = RandomStringUtils.randomNumeric(1,5);
            System.out.println(num);
        }
    }

    /**
     * 指定位数的字符串  (a-z, A-Z)
     */
    @Test
    public void testRandomAlphabetic(){
        for (int i = 0; i < 100; i++) {
            String s = RandomStringUtils.randomAlphabetic(5);
            System.out.println(s);
        }
    }


    /**
     * 指定位数的字符串  (a-z, A-Z)
     */
    @Test
    public void testRandomAlphabetic2(){
        for (int i = 0; i < 100; i++) {
            String s = RandomStringUtils.randomAlphabetic(1,5);
            System.out.println(s);
        }
    }

    /**
     * 指定位数的字符串   (a-z, A-Z) and  digits 0-9
     */
    @Test
    public void testRandomAlphanumeric(){
        for (int i = 0; i < 100; i++) {
            String s = RandomStringUtils.randomAlphanumeric(5);
            System.out.println(s);
        }
    }

    /**
     * 指定位数的字符串   (a-z, A-Z) and  digits 0-9
     */
    @Test
    public void testRandomAlphanumeric2(){
        for (int i = 0; i < 100; i++) {
            String s = RandomStringUtils.randomAlphanumeric(1,5);
            System.out.println(s);
        }
    }

}
