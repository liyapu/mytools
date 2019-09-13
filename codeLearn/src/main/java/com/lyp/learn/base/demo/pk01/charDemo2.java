package com.lyp.learn.base.demo.pk01;

public class charDemo2 {
    public static void main(String[] args) {
        //下面hi𝕆中的𝕆实际上是是一个辅助字符，它实际上占用了两个char来保存，
        // 这个字符串中总共为4个char，3个代码点。
        String str = "hi𝕆";
        int length = str.length();
        System.out.println("length is : " + length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            System.out.println(Integer.toHexString(charAt));
        }
        System.out.println("---------------");
        int codePointCount = str.codePointCount(0, length);
        System.out.println("codePointCount is : " + codePointCount);
        for (int i = 0; i < codePointCount; i++) {
            int charAt = str.codePointAt(i);
            System.out.println(Integer.toHexString(charAt));
        }

        System.out.println();
        //码点转化为字符串则需要使用一些特殊的API，比如：
        //判读是不是一个码点

        System.out.println(Character.isValidCodePoint(0xd835));
        System.out.println(Character.isValidCodePoint(0xdd46));
        System.out.println(Character.isValidCodePoint(0x1d546));
        System.out.println();
        //码点转化为字符串
        char[] chars = Character.toChars(0x1d546);
        String str3 = new String(chars);
        System.out.println(str3);//𝕆

    }
}
