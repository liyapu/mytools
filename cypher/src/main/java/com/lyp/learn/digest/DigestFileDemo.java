package com.lyp.learn.digest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * 获取文件消息摘要
 */
public class DigestFileDemo {

    public static void main(String[] args) throws Exception {
        String input = "aa";
        String algorithm = "MD5";

        // sha1 可以实现秒传功能
        // 使用 sha-1 算法，可以实现秒传功能，不管咱们如何修改文件的名字，最后得到的值是一样的
        String filePath = "/Users/liyapu/myGitRepository/mytools/cypher/src/main/resources/jetbrains-agent.jar";
        String filePath2 = "/Users/liyapu/myGitRepository/mytools/cypher/src/main/resources/jetbrains.jar";
        String sha1 = getDigestFile(filePath2, "SHA-1");
        System.out.println(sha1);
        System.out.println();

        String sha512 = getDigestFile(filePath2, "SHA-512");
        System.out.println(sha512);
        System.out.println();

        System.out.println("-------------");

        String md5 = getDigest("aa", "MD5");
        System.out.println(md5);
        System.out.println();

        String md51 = getDigest("aa ", "MD5");
        System.out.println(md51);
        System.out.println();
    }

    private static String getDigestFile(String filePath, String algorithm) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        int len;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        // 获取消息摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 获取消息摘要
        byte[] digest = messageDigest.digest(baos.toByteArray());
        System.out.println("密文的字节长度：" + digest.length);
        return toHex(digest);
    }

    private static String getDigest(String input, String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] digest = messageDigest.digest(input.getBytes());
        System.out.println("密文的字节长度：" + digest.length);
        return toHex(digest);
    }

    private static String toHex(byte[] digest) {
        //        System.out.println(new String(digest));
        // 消息摘要进行表示的时候，是用16进制进行表示
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            // 转成16进制

            String s = Integer.toHexString(b & 0xff);
            // 保持数据的完整性，前面不够的用0补齐
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println("16进制数据的长度:" + sb.toString().getBytes().length);
        return sb.toString();
    }
}
