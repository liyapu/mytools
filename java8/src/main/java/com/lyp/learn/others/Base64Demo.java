package com.lyp.learn.others;


import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * 在Java 8中，Base64编码已经成为Java类库的标准。
 * Java 8 内置了 Base64 编码的编码器和解码器。
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 *      基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
 *      URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 *      MIME：输出映射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 *
 * 内嵌类
 * 序号	        内嵌类                       描述
 * 1	static class Base64.Decoder    该类实现一个解码器用于，使用 Base64 编码来解码字节数据。
 *
 * 2	static class Base64.Encoder    该类实现一个编码器，使用 Base64 编码来编码字节数据。
 *
 * 方法
 * 序号	       方法名                                描述
 * 1	static Base64.Decoder getDecoder()    返回一个 Base64.Decoder ，解码使用基本型 base64 编码方案。
 *
 * 2	static Base64.Encoder getEncoder()    返回一个 Base64.Encoder ，编码使用基本型 base64 编码方案。
 *
 * 3	static Base64.Decoder getUrlDecoder()   返回一个 Base64.Decoder ，解码使用 URL 和文件名安全型 base64 编码方案。
 *
 * 4	static Base64.Encoder getUrlEncoder()  返回一个 Base64.Encoder ，编码使用 URL 和文件名安全型 base64 编码方案。
 *
 *
 * 5	static Base64.Decoder getMimeDecoder()  返回一个 Base64.Decoder ，解码使用 MIME 型 base64 编码方案。
 *
 * 6    static Base64.Encoder getMimeEncoder() 返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案。
 *
 * 7	static Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)
 *                     返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案，可以通过参数指定每行的长度及行的分隔符。
 *
 *
 *   注意：Base64 类的很多方法从 java.lang.Object 类继承。
 *
 *
 * Base64是一种用64个字符来表示任意二进制数据的方法。
 * Base64是一种任意二进制到文本字符串的编码方法，常用于在URL、Cookie、网页中传输少量二进制数据。
 *
 * Base64是网络上最常见的用于传输8Bit字节码的编码方式之一，Base64就是一种基于64个可打印字符来表示二进制数据的方法。
 *
 * 用记事本打开exe、jpg、pdf这些文件时，我们都会看到一大堆乱码，因为二进制文件包含很多无法显示和打印的字符，
 * 所以，如果要让记事本这样的文本处理软件能处理二进制数据，就需要一个二进制到字符串的转换方法。
 * Base64是一种最常见的二进制编码方法。
 *
 * 所以，Base64编码会把3字节的二进制数据编码为4字节的文本数据，长度增加33%，好处是编码后的文本数据可以在邮件正文、网页等直接显示。
 * 如果要编码的二进制数据不是3的倍数，最后会剩下1个或2个字节怎么办？Base64用\x00字节在末尾补足后，再在编码的末尾加上1个或2个=号，表示补了多少字节，解码的时候，会自动去掉
 *
 *由于标准的Base64编码后可能出现字符+和/，在URL中就不能直接作为参数，所以又有一种"url safe"的base64编码，其实就是把字符+和/分别变成-和_：
 *
 * Base64是一种通过查表的编码方法，不能用于加密，即使使用自定义的编码表也不行。
 *
 * Base64适用于小段内容的编码，比如数字证书签名、Cookie的内容等。
 *
 * 由于=字符也可能出现在Base64编码中，但=用在URL、Cookie里面会造成歧义，所以，很多Base64编码后会把=去掉：
 *
 *                  h              e               l           l           o
 * ASCII           104           101             108         108           111
 * 二进制      0110 1000       0110 0101         0110  1100  0110 1100    0110 1111
 * 6位分隔     0110 10,   00 0110， 0101   01，  10 1100，    0110 11， 00 0110， 1111 00  尾部不够补0
 * 十进制      26        6               21    44            27         6         60
 * base64码表  a        G                V      s           b           G         8
 *                         aGVsbG8=   (补2个0是一个等号，补4个0是两个等号)
 */

public class Base64Demo {

    /**
     * 使用基本编码
     */
    @Test
    public void test1() throws Exception{
        String encodeStr = "java 8 AA ZZ aa xyz 88 99 00";
        System.out.println("原始字符串:" + encodeStr );
        baseEncodeDecode(encodeStr);
        System.out.println("--------------------------------");

        String encodeStr2 = "AZ az 09 +/ ";
        System.out.println("原始字符串:" + encodeStr2 );
        baseEncodeDecode(encodeStr2);
        System.out.println("--------------------------------");

        String encodeStr3 = "AZ az 09 +/ 汉字汉字 中国 ";
        System.out.println("原始字符串:" + encodeStr3);
        baseEncodeDecode(encodeStr3);
        System.out.println("--------------------------------");

        String encodeStr4 = "AZ az 09 +/ 汉字汉字 中国 ~!@#$%^&*()_+<>? ";
        System.out.println("原始字符串:" + encodeStr4);
        baseEncodeDecode(encodeStr4);
        System.out.println("--------------------------------");


    }

    public void baseEncodeDecode(String str) throws Exception{
        String base64encodedString = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (基本) 编码后 :" + base64encodedString);

        // 解码
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        System.out.println("Base64 编码字符串 (基本) 解码后 :" + new String(base64decodedBytes, "utf-8"));
    }

    /**
     * url编码
     * @throws Exception
     */
    @Test
    public void test20() throws Exception{
        String encodeStr = "java 8 AA ZZ aa xyz 88 99 00";
        System.out.println("原始字符串:" + encodeStr );
        urlEncodeDecode(encodeStr);
        System.out.println("--------------------------------");


        String encodeStr2 = "AZ az 09 ++__Url ";
        System.out.println("原始字符串:" + encodeStr2 );
        urlEncodeDecode(encodeStr2);
        System.out.println("--------------------------------");

        String encodeStr3 = "AZ az 09 ++__Url 汉字汉字 中国 ";
        System.out.println("原始字符串:" + encodeStr3);
        urlEncodeDecode(encodeStr3);
        System.out.println("--------------------------------");

        String encodeStr4 = "AZ az 09 ++__Url 汉字汉字 中国 ~!@#$%^&*()_+<>? ";
        System.out.println("原始字符串:" + encodeStr4);
        urlEncodeDecode(encodeStr4);
        System.out.println("--------------------------------");
    }

    public void urlEncodeDecode(String str) throws Exception{
        String base64UrlEncodedString = Base64.getUrlEncoder().encodeToString(str.getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (URL) 编码后 :" + base64UrlEncodedString);
        byte[] base64UrlDecodedBytes = Base64.getUrlDecoder().decode(base64UrlEncodedString);
        System.out.println("Base64 编码字符串 (URL) 解码后 :" + new String(base64UrlDecodedBytes, "utf-8"));

    }

    /**
     * mime 编码
     * @throws Exception
     */
    @Test
    public void test30() throws Exception{
        String encodeStr = "java 8 AA ZZ aa xyz 88 99 00";
        System.out.println("原始字符串:" + encodeStr );
        mimeEncodeDecode(encodeStr);
        System.out.println("--------------------------------");

        String encodeStr2 = "A ZZ ayz 89 汉字汉字 中国 ~!@#$%^&*()_+<>? ???";
        System.out.println("原始字符串:" + encodeStr2);
        mimeEncodeDecode(encodeStr2);
        System.out.println("--------------------------------");
    }

    public void mimeEncodeDecode(String str) throws Exception{
        byte[] mimeBytes = str.getBytes("utf-8");
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码字符串 (MIME) 编码后:" + mimeEncodedString);
        byte [] mimeDecodeBytes = Base64.getMimeDecoder().decode(mimeEncodedString);
        System.out.println("Base64 编码字符串 (MIME) 解码后:" + new String(mimeDecodeBytes,"utf-8"));

    }
}
