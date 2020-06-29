package com.lyp.learn.symmetry;

import java.util.Base64;

/**
 * @author: liyapu
 * @description:
 * @date 2020-06-29 12:47
 */
public class Base64Demo {
    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("1".getBytes()));
        System.out.println(Base64.getEncoder().encodeToString("12".getBytes()));
        System.out.println(Base64.getEncoder().encodeToString("123".getBytes()));

        System.out.println();
        String str="TU0jV0xBTiNVYys5bEdiUjZlNU45aHJ0bTdDQStBPT0jNjQ2NDY1Njk4IzM5OTkwMDAwMzAwMA==";

        String rlt1=new String(Base64.getDecoder().decode(str));
        String rlt2=Base64.getDecoder().decode(str).toString();

        System.out.println(rlt1);
        System.out.println(rlt2);
        /*
            哪一个是正确的？为什么？
                 这里应该用new String()的方法，因为Base64加解密是一种转换编码格式的原理

            toString()与new String ()用法区别
                 str.toString是调用了这个object对象的类的toString方法。一般是返回这么一个String：[class name]@[hashCode]
                 new String(str)是根据parameter是一个字节数组，使用java虚拟机默认的编码格式，将这个字节数组decode为对应的字符。若虚拟机默认的编码格式是ISO-8859-1，按照ascii编码表即可得到字节对应的字符。

            什么时候用什么方法呢？
                new String（）一般使用字符转码的时候,byte[]数组的时候
                toString（）对象打印的时候使用
         */
    }

}