package com.lyp.learn.pk7;

/**
 *
 *  当int取值-1~5采用iconst指令，
 *  取值-128~127采用bipush指令，
 *  取值-32768~32767采用sipush指令，
 *  取值-2147483648~2147483647采用 ldc 指令
 *
 *  当取值 null 采用 aconst_null 指令
 *
 *  分析class文件，int取值0~5时JVM采用iconst_0、iconst_1、iconst_2、iconst_3、iconst_4、iconst_5指令将常量压入栈中，取值-1时采用iconst_m1指令将常量压入栈中。
 *
 *
 */
public class ByteDemo {
    public static void main(String[] args) {
        Object obj = null;
        int a = -2;
        int b = -1;
        int c = 0;
        int d = 1;
        int e = 2;
        int f = 3;
        int g = 4;
        int h = 5;
        int i = 6;
        int j = 7;
        int k = 8;
        int l = 9;
        int m = 10;
    }
}
