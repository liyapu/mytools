package com.lyp.learn.digest;

import java.security.MessageDigest;
import java.util.Base64;

/**
 *  其他数字摘要算法
 *    总结
 *     MD5算法 : 摘要结果16个字节, 转16进制后32个字节
 *     SHA1算法 : 摘要结果20个字节, 转16进制后40个字节
 *     SHA256算法 : 摘要结果32个字节, 转16进制后64个字节
 *     SHA512算法 : 摘要结果64个字节, 转16进制后128个字节
 */
public class DigestDemo1 {

    public static void main(String[] args) throws Exception {
        // 原文
        String input = "aa";

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

//        System.out.println(new String(digest));
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

