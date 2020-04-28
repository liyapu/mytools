package com.lyp.learn.others;

import org.junit.jupiter.api.Test;

/**
 *
 *
 * String：jdk 8 及之前：底层使用char[]存储；jdk 9 : 底层使用byte[] (encoding flag)
 * StringBuffer:jdk 8 及之前：底层使用char[]存储；jdk 9 : 底层使用byte[]
 * StringBuilder:jdk 8 及之前：底层使用char[]存储；jdk 9 : 底层使用byte[]
 *
 * String：不可变的字符序列；
 * StringBuffer:可变的字符序列；线程安全的，效率低；
 * StringBuilder:可变的字符序列；线程不安全的，效率高（jdk 5.0)
 *
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "aaa";

        String s = "    ss    s      ";
        String zhong = "  中文  空白  ";

        //判断是否为空
        System.out.println(s.isEmpty());
        //判断是否都是空白
        System.out.println(s.isBlank());
        System.out.println("     ".isBlank());
        //去除首尾空白 可以去除其它语言中的空白，包括英文和其它语言中的空白字符
        System.out.println(s.strip());
        System.out.println(zhong.strip());
        System.out.println(zhong.strip().length());
        //去除首尾的空白 无法去除 非英文的空白。只能出去 unicode 码值小于32的空白字符
        System.out.println(s.trim());
        System.out.println(zhong.trim());
        System.out.println(zhong.trim().length());
        //去除开头的空白
        System.out.println(s.stripLeading());
        // 去除尾部的空白
        System.out.println(s.stripTrailing());
//        System.out.println(s.stripIndent());

        System.out.println("a".repeat(5));
    }

    @Test
    public void testLines(){
        String s = "aa \r bb\r cc";
        s.lines().forEach(System.out::println);

        long count = s.lines().count();
        System.out.println(count);
    }
}

