package com.lyp.learn.symmetry;

/**
 *   对称加密
 *       采用单钥密码系统的加密方法，同一个密钥可以同时用作信息的加密和解密，这种加密方法称为 对称加密 ，也称为 单密钥加密。
 *
 *       示例
 *           我们现在有一个原文3要发送给B
 *           设置密钥为108, 3 * 108 = 324, 将324作为密文发送给B
 *            B拿到密文324后, 使用324/108 = 3 得到原文
 *
 *      常见加密算法
 *         DES : Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法，
 *               1977年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），并授权在非密级政府通信中使用，随后该算法在国际上广泛流传开来。
 *
 *         AES : Advanced Encryption Standard, 高级加密标准 .在密码学中又称Rijndael加密法，
 *               是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
 *
 *         特点
 *             加密速度快, 可以加密大文件
 *             密文可逆, 一旦密钥文件泄漏, 就会导致数据暴露
 *             加密后编码表找不到对应字符, 出现乱码
 *             一般结合Base64使用

 */
public interface Info {
}