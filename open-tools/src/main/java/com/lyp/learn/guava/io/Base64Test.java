package com.lyp.learn.guava.io;

import com.google.common.io.BaseEncoding;
import org.junit.jupiter.api.Test;

public class Base64Test {
    /**
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
    @Test
    public void testBase64(){
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
        System.out.println(new String(baseEncoding.decode("aGVsbG8=")));

        System.out.println();
        System.out.println(baseEncoding.omitPadding().encode("hello".getBytes()));
        System.out.println(new String(baseEncoding.omitPadding().decode("aGVsbG8")));
    }

    @Test
    public void testMyBase64(){
        System.out.println(BaseEncoding.base64().encode(("abbcdee").getBytes()));
        System.out.println(MyBase.encode("abbcdee"));
        System.out.println(BaseEncoding.base64().encode(("TTJHG23243jdfjdk").getBytes()));
        System.out.println(MyBase.encode("TTJHG23243jdfjdk"));
        System.out.println(BaseEncoding.base64().encode(("aabYHLLLK28384!//.,///@#^&*").getBytes()));
        System.out.println(MyBase.encode("aabYHLLLK28384!//.,///@#^&*"));
        System.out.println();
        System.out.println(new String(BaseEncoding.base64().decode("YWJiY2RlZQ==")));
        System.out.println(MyBase.decode("YWJiY2RlZQ=="));
        System.out.println(new String(BaseEncoding.base64().decode("VFRKSEcyMzI0M2pkZmpkaw==")));
        System.out.println(MyBase.decode("VFRKSEcyMzI0M2pkZmpkaw=="));
        System.out.println(new String(BaseEncoding.base64().decode("YWFiWUhMTExLMjgzODQhLy8uLC8vL0AjXiYq")));
        System.out.println(MyBase.decode("YWFiWUhMTExLMjgzODQhLy8uLC8vL0AjXiYq"));
    }
    @Test
    public void testBaseUrl(){
        BaseEncoding baseEncoding = BaseEncoding.base64Url();
        System.out.println(baseEncoding.encode("hello ///".getBytes()));
        System.out.println(new String(baseEncoding.decode("aGVsbG8gLy8v")));
    }
}
