package com.lyp.learn.symmetry.aes;


import org.apache.xmlbeans.impl.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * des加密算法
 * <p>
 * Cipher ：文档 https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
 *
 *  AES 加密解密和 DES 加密解密代码一样，只需要修改加密算法就行，拷贝 DES 代码
 *
 *  运行程序：AES 加密的密钥key ， 需要传入16个字节
 *
 */
public class AESDemo {
    // AES加密算法，比较高级，所以key的大小必须是16个字节

    public static void main(String[] args) throws Exception {
        String input = "硅谷aa算法      是对方是否是的aa abb123243511~!@#$%^&*()O aad ";
        // AES加密算法，key的大小必须是16个字节
        String key = "1234567812345678";

        String transformation = "AES";
        // 指定获取密钥的算法
        String algorithm = "AES";

        String encryptAES = encryptAES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptAES);

        String s = decryptAES(encryptAES, key, transformation, algorithm);
        System.out.println("解密:" + s);

    }

    /**
     * 使用AES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(AES,密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception
     */
    private static String encryptAES(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        //表示模式，有加密模式和解密模式
        // ENCRYPT_MODE：加密模式
        // DECRYPT_MODE: 解密模式
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        //打印字节，因为ascii码有负数，解析不出来，所以乱码,用Base64编码一下
        //输出加密后的数据
        String encode = new String(Base64.encode(bytes));
        return encode;
    }

    /**
     * 使用AES解密
     *
     * @param input          : 密文
     * @param key            : 密钥
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @throws Exception
     * @return: 原文
     */
    private static String decryptAES(String input, String key, String transformation, String algorithm) throws Exception {
        // 1,获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定密钥规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE, sks);
        // 3. 解密，上面使用的base64编码，下面直接用密文
        byte[] bytes = cipher.doFinal(Base64.decode(input.getBytes()));
        //  因为是明文，所以直接返回
        return new String(bytes);
    }
}