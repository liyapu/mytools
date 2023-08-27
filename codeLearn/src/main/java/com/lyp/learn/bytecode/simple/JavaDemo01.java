package com.lyp.learn.bytecode.simple;

/**
 * @author liyapu
 * @date 2023-08-16 16:15
 * @description
 */
public class JavaDemo01 {
    static Long result = 0L;

    static final String MY_CONST = "const";

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
//        int c = a + b;
//        result = Integer.toUnsignedLong(c);
//        System.out.println("c = " + c);
//        System.out.println("result = " + result);

        JavaDemo01 jd = new JavaDemo01();
        final int result = jd.mul(a, b);
        System.out.println("result = " + result);

        String strA = "aa";
        String r = jd.appendStr(strA);
        System.out.println("r = " + r);
    }

    public synchronized int mul(int a, int b) {
        int c = 1;
        int d = 2;
        int e = 3;
        int f = 4;
        int g = 5;
        int h = 6;
        int i = 7;
        return a * b * c * d * e * f * g * h * i;
    }

    public String appendStr(String str) {
        String r = MY_CONST + str;
        return r;
    }
}
