package com.lyp.learn.base.demo.pk01;

public class charDemo1 {
    public static void main(String[] args) {
        /**
         * char 类型原本用于表示单个字符
         * char 类型的字面量值要用单引号括起来。
         * char类型表示的是单个字符类型，任何数据使用单引号括起来的都是表示字符。
         * 字符只能有一个字符。
         * 例如：'A' 是编码值为 65 所对应的字符常量。
         * 它与 "A" 不同，"A" 是包含一个字符 A 的字符串。
         * char 类型的值可以表示为十六进制值，其范围从 \u0000 到 \Uffff
         *
         * java中的char类型占2个字节，一个字节8位，共16位。
         * 汉字占2个字节，字母和数字占1个字节
         *
         */
        char temp1 =  'A';
        char temp2 = 'a';
        char temp3 = '0';
        char temp4 = '1';
        char temp5 = '中';
        char temp6 = '国';
        char temp7 = '\u0041';
        char temp8 = '\u0042';
        char temp9 = '\u005a';
        char temp10 = '\u0061';
        char temp11 = '\u007a';


        System.out.println(temp1);
        System.out.println((int)temp1);
        System.out.println(temp2);
        System.out.println((int)temp2);
        System.out.println(temp3);
        System.out.println((int)temp3);
        System.out.println(temp4);
        System.out.println((int)temp4);
        System.out.println(temp5);
        System.out.println((int)temp5);
        System.out.println(temp6);
        System.out.println((int)temp6);
        System.out.println(temp7);
        System.out.println((int)temp7);
        System.out.println(temp8);
        System.out.println((int)temp8);
        System.out.println(temp9);
        System.out.println((int)temp9);
        System.out.println(temp10);
        System.out.println((int)temp10);
        System.out.println(temp11);
        System.out.println((int)temp11);
        System.out.println(Character.getNumericValue(temp1));
        System.out.println();

        System.out.println("-------------");
        String str = "abcdefg";
        System.out.println(str.charAt(1));
        System.out.println(str.charAt(3));
        System.out.println(str.charAt(5));
        System.out.println();

        System.out.println("--------------");
        System.out.println(Character.isWhitespace(temp1));
        System.out.println(Character.isWhitespace('\t'));
    }
}
