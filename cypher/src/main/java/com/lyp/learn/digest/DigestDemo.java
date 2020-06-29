package com.lyp.learn.digest;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;

/**
 * @author: liyapu
 * @description:
 * @date 2020-06-29 14:26
 */
public class DigestDemo {
    public static void main(String[] args) throws Exception{
        // 原文
        String input = "aa";
        // 算法
        String algorithm = "MD5";
//        Provider[] providers = Security.getProviders();
//        for (Provider provider : providers) {
//            System.out.println(provider.getName());
//        }
        // 获取数字摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 获取消息数字摘要的字节数组
        byte[] digest = messageDigest.digest(input.getBytes());
        //base64编码
        System.out.println(new String(Base64.getEncoder().encode(digest)));

        //使用在线 md5 加密 ，发现我们生成的值和代码生成的值不一样，
        // 那是因为消息摘要不是使用base64进行编码的，所以我们需要把值转成16进制

        //数字摘要转换成 16 进制
        // 4124bc0a9335c27f086f24ba207a4912     md5 在线校验
        // QSS8CpM1wn8IbyS6IHpJEg==             消息摘要使用的是16进制


        // 创建对象用来拼接
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            //System.out.println(s);
            if (s.length() == 1){
                // 如果生成的字符只有一个，前面补0
                s = "0"+s;
            }
            sb.append(s);
        }
        System.out.println(sb.toString());


    }
}
