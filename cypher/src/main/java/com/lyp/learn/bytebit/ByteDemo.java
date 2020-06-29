package com.lyp.learn.bytebit;

import java.io.UnsupportedEncodingException;

/**
 * Byte : 字节. 数据存储的基本单位，比如移动硬盘1T ， 单位是byte
 * <p>
 * bit : 比特, 又叫位. 一个位要么是0要么是1.
 * 数据传输的单位 , 比如家里的宽带100MB，下载速度并没有达到100MB，一般都是12-13MB，
 * 那么是因为需要使用 100 / 8
 * <p>
 * 关系: 1Byte = 8bit
 */
public class ByteDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("-------获取字符串byte--------");
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c = b;
            // 打印发现byte实际上就是ascii码
            System.out.println(c);
            // byte对应bit
            // 我们在来看看每个byte对应的bit，byte获取对应的bit
            String s = Integer.toBinaryString(c);
            System.out.println(s);
        }

        System.out.println("-----中文 utf-8--------------");
        chineseUtf8();
        System.out.println("-----中文 gbk--------------");
        chineseGBK();

        System.out.println("-----英文对应的字节--------------");
        englishCode();
    }

    /*
      中文对应的字节
         中文在GBK编码下, 占据2个字节
         中文在UTF-8编码下, 占据3个字节
     */
    public static void chineseUtf8(){
        String a = "尚";
        byte[] bytes = a.getBytes();
        //发现一个中文是有 3 个字节组成
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }

    public static void chineseGBK() throws Exception {
        String a = "尚";

        // 在中文情况下，不同的编码格式，对应不同的字节
        // GBK :编码格式占2个字节
        // UTF-8：编码格式占3个字节
        byte[] bytes = a.getBytes("GBK");
//         byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }

    //英文对应的字节
    public static void englishCode(){
        String a = "A";
        byte[] bytes = a.getBytes();
        // 在中文情况下，不同的编码格式，对应不同的字节
//        byte[] bytes = a.getBytes("GBK");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }


}
