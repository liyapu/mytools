package com.lyp.learn.digest;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * 其他数字摘要算法
 */
public class DigestDemo1 {
    public static void main(String[] args) throws Exception {
        // 4124bc0a9335c27f086f24ba207a4912     md5 在线校验
        // QSS8CpM1wn8IbyS6IHpJEg==             消息摘要使用的是16进制
        // 原文
        String input = "aa";
        // 算法
        String algorithm = "MD5";
        // 获取数字摘要对象
        String md5 = getDigest(input, "MD5");
        System.out.println(md5);
        System.out.println();

        String sha1 = getDigest(input, "SHA-1");
        System.out.println(sha1);
        System.out.println();

        String sha256 = getDigest(input, "SHA-256");
        System.out.println(sha256);
        System.out.println();


        String sha512 = getDigest(input, "SHA-512");
        System.out.println(sha512);
        System.out.println();



    }


    private static String getDigest(String input, String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 消息数字摘要
        byte[] digest = messageDigest.digest(input.getBytes());
        System.out.println("密文的字节长度:" + digest.length);

        return toHex(digest);
    }

    private static String toHex(byte[] digest) throws Exception {

        System.out.println(new String(digest));
        // base64编码
        System.out.println("base64编码: " + new String(Base64.getEncoder().encode(digest)));
        // 创建对象用来拼接
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println("16进制数据的长度：" + sb.toString().getBytes().length);
        return sb.toString();
    }
}

