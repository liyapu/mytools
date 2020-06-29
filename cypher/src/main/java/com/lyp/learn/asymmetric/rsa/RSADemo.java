package com.lyp.learn.asymmetric.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 */
public class RSADemo {
    public static void main(String[] args) throws Exception {

        // 加密算法
        String algorithm = "RSA";
        //  创建密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();

        // 获取私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();

        // 对公私钥进行base64编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);

        // 打印私钥
        System.out.println(privateKeyString);

        // 打印公钥
        System.out.println(publicKeyString);

        System.out.println("-----------------------");
        String input = "硅谷456";
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数:加密的模式
        // 第二个参数：使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        // 私钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        String miBase64 = Base64.encode(bytes);
        System.out.println(miBase64);


        // 私钥进行解密
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        // 对密文进行解密，不需要使用base64，因为原文不会乱码
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println(new String(bytes1));

        //因为代码每次执行，都会 重新生成 公钥和私钥，所以无法 复制下面输出的 miBase64，进行解密，只能通过  Base64.decode(miBase64) 进行解密
//        String mi = "O2VOHKU0DVFWwqYMex/KImXXw67NAFhbK+SK5BZjZQiczlrhOB2HAhDjE03KEb7//KZO4JZO/SooP47DsJDkdrpVvEjFedQ/XKyOMaGL9vnjc9dCfQkjKnLx9D4gWX3oCU5xACeE9RRnYfbIYPeXiqwAxe75iF0TtuNtE6NRILkv/XXC5108/AnrCqBQL51cAGxHg2BE3IizERUqvAhRdK5giuYT+7B5/D2Y4OBiHE0nB69DZMooajIS6TEhQZtMQhmB3kFSoxHUjvYQExBsZkZYKQUePyolqZgCJAQ9SAObys6FsTC7PxWdvN2mGfRdbFBI7w7IvrmFWhz8kGRheA==";
//        byte[] decode2 = Base64.decode(mi); //

        //因为输出文本经过了base64,所以这里也要 反解一下 base64
        byte[] decode2 = Base64.decode(miBase64);
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        byte[] bytes2 = cipher.doFinal(decode2);
        System.out.println(new String(bytes2));
    }
}
