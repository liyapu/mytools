package com.lyp.learn.guava.io;

import com.google.common.io.CharSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 字符的方式读取文件
 * CharSource ---> Reader
 * CharSink ---> Writer
 */
public class CharSourceTest {

    @Test
    public void testCharSource() throws IOException {
        CharSource charSource = CharSource.wrap("i am a text for charSource \r second line test");
        String read = charSource.read();
        System.out.println(read);
        System.out.println(charSource.isEmpty());
        System.out.println(charSource.length());
        System.out.println(charSource.readFirstLine());
    }

    /**
     * 从多个CharSource 读取
     */
    @Test
    public void testConcat() throws IOException {
        CharSource charSource = CharSource.concat(
                CharSource.wrap(" content 11111\n"),
                CharSource.wrap(" content 2222")
        );

        String read = charSource.read();
        System.out.println(read);
    }
}
