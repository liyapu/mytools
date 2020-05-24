package com.lyp.learn.pk7;

import org.junit.jupiter.api.Test;

/**
 *   如何保证变量s指向的是字符串常量池中的数据呢?
 *   有两种方式
 *      方式一:
 *              String s = "hello"; //字面量定义的方式
 *      方式二:  调用 intern()方法
 *              String s = new String("hello).intern();
 *              String s = new StringBuilder("hello").toString().intern();
 *
 *
 */
public class StringIntern {

    @Test
    public void test1() {
        String s1 = new String("a");
        String s3 = s1.intern();

        String s2 = "a";
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);
    }

    @Test
    public void test2() {
        String s1 = new String("a").intern();

        String s2 = "a";
        System.out.println(s1 == s2);
    }

    @Test
    public void test3(){
        String s1 = new String("a") + new String("a");
        s1.intern();

        String s2 = "aa";
        System.out.println(s1 == s2);
    }

}














