package com.lyp.learn.ascii;

/**
 * AsciiDemo
 *
 * @Author: 马伟奇
 * @CreateTime: 2020-05-05
 * @Description:
 */
public class AsciiDemo {

    public static void main(String[] args) {
        char A = 'A';
        int AInt = A;
        System.out.println((int)A);
        System.out.println(AInt);
        System.out.println((int)'B');
        System.out.println((int)'C');
        System.out.println();

        System.out.println((int)'a');
        System.out.println((int)'b');
        System.out.println((int)'c');
        System.out.println();
        // 定义字符串
        String a = "AaBbCcZz";
        // 需要拆开字符串
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int asciicode = aChar;
            System.out.println(asciicode);
        }
    }
}