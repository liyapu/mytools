package com.lyp.learn.guava.base;

import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

public class StringsTest {

    /**
     *  如果为null 转为""
     */
    @Test
    public void testNullToEmpty(){
        System.out.println(Strings.nullToEmpty(null));
        System.out.println(Strings.nullToEmpty(null).length());
        System.out.println(Strings.nullToEmpty("aa"));
    }

    /**
     * 如果为"" 转为null
     */
    @Test
    public void testEmptyToNull(){
        System.out.println(Strings.emptyToNull(null) == null);
        System.out.println(Strings.emptyToNull("") == null);
        System.out.println(Strings.emptyToNull(" ") == null);
        System.out.println(Strings.emptyToNull("aaa"));

    }

    /**
     * 判断是否为空
     * 多个空白，不算为空
     */
    @Test
    public void testIsNullOrEmpty(){
        System.out.println(Strings.isNullOrEmpty(null));
        System.out.println(Strings.isNullOrEmpty(""));
        System.out.println(Strings.isNullOrEmpty("   "));
        System.out.println(Strings.isNullOrEmpty("aa"));
    }

    /**
     * 在前部添加
     */
    @Test
    public void testPadStart(){
        System.out.println(Strings.padStart("aaa",-1,'$'));
        System.out.println(Strings.padStart("aaa",0,'$'));
        System.out.println(Strings.padStart("aaa",2,'$'));
        System.out.println(Strings.padStart("aaa",5,'$'));
    }

    /**
     * 在尾部添加
     */
    @Test
    public void testPadEnd(){
        System.out.println(Strings.padEnd("aaa",-1,'$'));
        System.out.println(Strings.padEnd("aaa",0,'$'));
        System.out.println(Strings.padEnd("aaa",2,'$'));
        System.out.println(Strings.padEnd("aaa",5,'$'));
    }

    @Test
    public void testRepeat(){
        System.out.println(Strings.repeat("Hi",0));
        System.out.println(Strings.repeat("Hi",1));
        System.out.println(Strings.repeat("Hi",3));
    }

    /**
     * 共同前缀
     */
    @Test
    public void testCommonPrefix(){
        System.out.println(Strings.commonPrefix("hello","you"));
        System.out.println(Strings.commonPrefix("hello","hi"));
        System.out.println(Strings.commonPrefix("hello","he"));
    }

    /**
     * 共同后缀
     */
    @Test
    public void testCommonSuffix(){
        System.out.println(Strings.commonSuffix("hello","you"));
        System.out.println(Strings.commonSuffix("hello","echo"));
        System.out.println(Strings.commonSuffix("hello","allo"));
    }

    /**
     * %s 和 占位数 要匹配，少了报异常，占位数多了没有影响
     */
    @Test
    public void testStringFormat(){
        System.out.println(String.format("%s位 %s位 %s 位 %s 位",1,2,3,5));
        System.out.println(String.format("%s位 %s位 %s 位",1,2,3,5));
        System.out.println(String.format("%s位 %s位 %s 位 %s 位",1,2));
    }

    /**
     * 字符串格式化
     * 容忍  %s 和 占位数 个数不匹配, 多了少了都行
     */
    @Test
    public void testLenientFormat(){
        System.out.println(Strings.lenientFormat("%s位 %s位 %s 位 %s 位",1,2,3,5));
        System.out.println(Strings.lenientFormat("%s位 %s位 %s 位",1,2,3,5));
        System.out.println(Strings.lenientFormat("%s位 %s位 %s 位 %s 位",1,2));
    }

}