package com.lyp.learn.apachecommons.io;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-31 11:31
 */
public class IOUtilsTest {
    public static String inDirStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/out";
    public static String outDirStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/out";
    public static String fileStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/source.txt";
    public static String tempDir ="/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp";


    /**
     * 静态变量
     */
    @Test
    public void testStaticConstant(){
        //获取文件路径分隔符字符
        char dirSeparator = IOUtils.DIR_SEPARATOR;
        //获取Unix系统文件路径分隔符字符
        char dirSeparatorUnix = IOUtils.DIR_SEPARATOR_UNIX;
        //获取windows系统路径分隔符字符
        char dirSeparatorWindows = IOUtils.DIR_SEPARATOR_WINDOWS;
        
        
        //获取行分隔符
        String lineSeparator = IOUtils.LINE_SEPARATOR;
        //获取Unix系统行分隔符
        String lineSeparatorUnix = IOUtils.LINE_SEPARATOR_UNIX;
        //获取windows系统行分隔符
        String lineSeparatorWindows = IOUtils.LINE_SEPARATOR_WINDOWS;
    }

    /**
     * 读取文件
     */
    @Test
    public void testRead() throws IOException {
        File sourceFile = new File(fileStr);
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        Reader reader = new FileReader(fileStr);
        
        //根据InputStream 获取 BufferedInputStream
        BufferedInputStream bufferedInputStream = IOUtils.buffer(fileInputStream);
        
        //从Reader获取文件字节数组，并指定编码
        byte[] bytes = IOUtils.toByteArray(reader, "UTF-8");

        //从InputStream输入流中获取行数据
        List<String> stringList = IOUtils.readLines(fileInputStream, "UTF-8");
        //从InputStream中获取字符串数据
        String s = IOUtils.toString(fileInputStream, "UTF-8");
    }

    /**
     * 写入文件
     */
    @Test
    public void testWrite() throws IOException {
        File sourceFile = new File(fileStr);
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        Writer writer = new FileWriter(fileStr);
    }

    /**
     * 复制文件
     */

    /**
     * 输入流迭代器
     */
    @Test
    public void testIterator() throws IOException {
        File sourceFile = new File(fileStr);
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        Reader reader = new FileReader(fileStr);
        //获取文件输入迭代器
        LineIterator lineIterator = IOUtils.lineIterator(fileInputStream, "UTF-8");
        
        //从Reader输入流中获取行迭代器
        LineIterator lineIterator1 = IOUtils.lineIterator(reader);
    }
}
