package com.lyp.learn.guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 字符的方式 写文件
 *  CharSource ---> Reader
 *  CharSink ---> Writer
 */
public class CharSinkTest {

    public static final String WRITE_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\write.txt";

    @Test
    public void test1() throws IOException {
        Files.asCharSink(new File(WRITE_FILE), Charsets.UTF_8, FileWriteMode.APPEND).write("测试是");
    }
}
