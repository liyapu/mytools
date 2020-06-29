package com.lyp.learn.sign2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.PublicKey;

/**
 * @author: liyapu
 * @description:
 * @date 2020-06-29 16:49
 */
public class UserController {
//    @ApiOperation(value = "购物")
//    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy(String price, String num, String signature) {
        try {
            // 获取公钥
            PublicKey publicKey = RsaDemo.loadPublicKeyFromFile("RSA", "a.pub");
            // 第一个参数：原文
            // 第二个参数：算法
            // 第三个参数：公钥
            // 第四个参数：签名
            boolean result = SignatureDemo.verifySignature(price + num, "SHA256withRSA", publicKey, signature);

            if (result) {
                return "购物成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "购物失败";
    }
}
