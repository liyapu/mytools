package com.lyp.learn.guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 字节方式 读取
 *
 * ByteSource ---> InputStream
 * ByteSink  -----> OutputStream
 */
public class ByteSourceTest {
    private static final String SOURCE_FILE_IMG = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\taiji.png";
    private static final String TARGET_FILE_IMG = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\target.png";

    @Test
    public void testRead() throws IOException {
        File sourceFile = new File(SOURCE_FILE_IMG);
        ByteSource byteSource = Files.asByteSource(sourceFile);
        byte[] read = byteSource.read();

        System.out.println(read.length);
        System.out.println(Files.toByteArray(sourceFile).length);
    }

    /**
     * 复制图片
     */
    @Test
    public void testAsByteSource() throws IOException {
        File sourceFile = new File(SOURCE_FILE_IMG);
        ByteSource byteSource = Files.asByteSource(sourceFile);
        byte[] read = byteSource.read();

        File targetFile = new File(TARGET_FILE_IMG);
        Files.asByteSink(targetFile, FileWriteMode.APPEND).write(read);
    }

    /**
     * 复制图片
     */
    @Test
    public void testAsByteSource2() throws IOException {
        File sourceFile = new File(SOURCE_FILE_IMG);
        ByteSource byteSource = Files.asByteSource(sourceFile);

        File targetFile = new File(TARGET_FILE_IMG);
        byteSource.copyTo(Files.asByteSink(targetFile));
    }

    /**
     * 复制图片
     */
    @Test
    public void testCopy() throws IOException {
        Files.copy(new File(SOURCE_FILE_IMG),new File(TARGET_FILE_IMG));
    }

    /**
     * slice  分片
     */
    @Test
    public void testSlice() throws IOException {
        ByteSource byteSource = ByteSource.wrap(new byte[] {0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09});
        ByteSource slice = byteSource.slice(5, 5);
        byte[] bytes = slice.read();
        for(byte b : bytes){
            System.out.println(b);
        }
    }


}
