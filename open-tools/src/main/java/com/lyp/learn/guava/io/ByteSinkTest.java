package com.lyp.learn.guava.io;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 字节方式 写入
 *
 * ByteSource ---> InputStream
 * ByteSink  -----> OutputStream
 */
public class ByteSinkTest {
    public static final String WRITE_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\write.txt";

    @Test
    public void test() throws IOException {
        File file = new File(WRITE_FILE);
        Files.asByteSink(file, FileWriteMode.APPEND).write("byteSink test 测试的 test".getBytes());

    }
}
