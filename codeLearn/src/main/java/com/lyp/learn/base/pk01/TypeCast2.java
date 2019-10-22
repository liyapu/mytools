package com.lyp.learn.base.pk01;

public class TypeCast2 {
    public static void main(String[] args) {
        byte b1 = 10;
        //编译错误： Incompatible types. Found: 'int', required: 'byte'
        /**
         * 由于b1+1运算时会自动提升表达式的类型，所以结果是int型，
         * 再赋值给byte类型sb2时，编译器将报告需要强制转换类型的错误。
         * 解决方式：
         * 第一种方式:使用int类型接收
         * 第二种方式:使用(byte)强制转换为byte类型
         */

        //byte b2 = b1 + 1;
        int i1 = b1 + 1;
        byte b2 = (byte)(b1 + 1);
        //Incompatible types. Found: 'int', required: 'byte'
        //b2 = b1 + b2;
        b1++;
        byte b3 = b1;
        //由于 += 是Java语言规定的运算符，java编译器会对它进行特殊处理，因此可以正确编译
        b1 += 1;
        byte b33 = b1;
        System.out.println();

        final byte b7 = 100;
        //Cannot assign a value to final variable 'b7'
        //b7 = b7 + 1;
        //b7 += 1;
        byte b8 = b7 + 1;

    }
}
