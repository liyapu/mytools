package com.lyp.learn.symmetry.des;


import org.apache.xmlbeans.impl.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * des加密算法
 *
 * Cipher ：文档 https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
 *
 *  key="123456"
 *  密钥是6个字节，DES加密算法规定，密钥key必须是8个字节，所以需要修改上面key改成8个字节的 key="12345678"
 *  修改 密钥 key = “12345678”
 *  再次运行 ，出现乱码是因为对应的字节出现负数，但负数，没有出现在 ascii 码表里面，所以出现乱码，需要配合base64进行转码
 *  使用 base64 进行编码
 *  base64 导包的时候，需要注意 ，别导错了，需要导入 apache 包
 */
public class DESDemo {

    // DES加密算法,key的大小必须是8个字节
    public static void main(String[] args) throws Exception {
        String input ="硅谷aa算法      是对方是否是的aa abb123243511~!@#$%^&*()O aad ";
        // DES加密算法，key的大小必须是8个字节
        String key = "12345678";

        String transformation = "DES";
        // 指定获取密钥的算法
        String algorithm = "DES";

        String encryptDES = encryptDES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptDES);

        String s = decryptDES(encryptDES, key, transformation, algorithm);
        System.out.println("解密:" + s);

    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES,密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception
     */
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
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
        cipher.init(Cipher.ENCRYPT_MODE,sks);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        //打印字节，因为ascii码有负数，解析不出来，所以乱码,用Base64编码一下
        //输出加密后的数据
        String encode = new String(Base64.encode(bytes));
        return encode;
    }

    /**
     * 使用DES解密
     *
     * @param input          : 密文
     * @param key            : 密钥
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @throws Exception
     * @return: 原文
     */
    private static String decryptDES(String input, String key, String transformation, String algorithm) throws Exception {
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