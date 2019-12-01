package com.lyp.learn.file;

import org.junit.Test;

import java.io.File;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-16 17:02
 */
public class FileTest {
    @Test
    public void testFile(){
        File file = new File("/ip.txt");
        System.out.println(file.getName());
        System.out.println(file.isFile());
        System.out.println(file.toPath().toUri().toString());
        System.out.println(File.separator+"");
    }
}
