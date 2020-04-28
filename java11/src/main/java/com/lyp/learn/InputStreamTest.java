package com.lyp.learn;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-28 16:45
 */
public class InputStreamTest {

    /**
     *   InputStream 加强
     *      InputStream 终于有了一个非常有用的方法: transTo()
     *      可以用来将数据直接传输到 OutputStream
     *
     *      这是在处理原始数据流时非常常见的一种用法
     */
    @Test
    public void test01() throws IOException {

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("test.txt");
            FileOutputStream fos = new FileOutputStream("copy")) {

            //把输入流汇总的所有数据直接自动地复制到输出流中
            is.transferTo(fos);
        }
    }
}









