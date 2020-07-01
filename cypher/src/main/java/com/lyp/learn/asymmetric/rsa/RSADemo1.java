package com.lyp.learn.asymmetric.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;


/**
 *   前面代码每次都会生成 加密和解密 ，
 *   咱们需要把加密和解密的方法全部到本地的根目录下面。
 */
public class RSADemo1 {
    public static void main(String[] args) throws Exception {
//        String input = "硅谷";
        // 加密算法
        String algorithm = "RSA";

        //生成密钥对并保存在本地文件中
        generateKeyToFile(algorithm, "a.pub", "a.pri");

        //加密
//        String s = encryptRSA(algorithm, privateKey, input);
//        // 解密
//        String s1 = decryptRSA(algorithm, publicKey, s);
//        System.out.println(s1);


    }

    /**
     * 生成密钥对并保存在本地文件中
     *
     * @param algorithm : 算法
     * @param pubPath   : 公钥保存路径
     * @param priPath   : 私钥保存路径
     * @throws Exception
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        // 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();

        // 获取byte数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();

        // 进行Base64编码
        String publicKeyString = Base64.encode(publicKeyEncoded);
        String privateKeyString = Base64.encode(privateKeyEncoded);

        // 保存文件  把公钥和私钥保存到根目录
        FileUtils.writeStringToFile(new File(pubPath), publicKeyString, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateKeyString, Charset.forName("UTF-8"));

    }

    /**
     * 解密数据
     *
     * @param algorithm : 算法
     * @param encrypted : 密文
     * @param publicKey : 公钥
     * @return : 原文
     * @throws Exception
     */
    public static String decryptRSA(String algorithm, Key publicKey, String encrypted) throws Exception {
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 私钥进行解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        // 由于密文进行了Base64编码, 在这里需要进行解码
        byte[] decode = Base64.decode(encrypted);
        byte[] bytes1 = cipher.doFinal(decode);
        String mingWen = new String(bytes1);
        return mingWen;
    }

    /**
     * 使用密钥加密数据
     *
     * @param algorithm : 算法
     * @param input     : 原文
     * @param privateKey : 私钥
     * @return : 密文
     * @throws Exception
     */
    public static String encryptRSA(String algorithm, Key privateKey, String input) throws Exception {
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数:加密的模式
        // 第二个参数：使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 私钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 对密文进行Base64编码
        System.out.println(Base64.encode(bytes));
        return Base64.encode(bytes);
    }
}