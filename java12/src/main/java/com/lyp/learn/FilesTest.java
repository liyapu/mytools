//package com.lyp.learn;
//
//
//import org.junit.jupiter.api.Test;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
///**
// * @author shkstart
// * @create 2019 下午 8:46
// */
//public class FilesTest {
//
//    //使用IDEA的单元测试方法，默认的相对路径是在当前module下
//    //IO : File
//    //NIO 2 : Files操作本地文件的工具类  ； Path:替换原有的File ;   Paths：用来生成实例化Path
//    @Test
//    public void testFilesMismatch() throws IOException {
//        // 文件可以不存在，但是 tmp目录需要存在
//        FileWriter fileWriter = new FileWriter("tmp\\a.txt");
//        fileWriter.write("a");
//        fileWriter.write("b");
//        fileWriter.write("c");
//        fileWriter.close();
//
//        FileWriter fileWriterB = new FileWriter("tmp\\b.txt");
//        fileWriterB.write("a");
//        fileWriterB.write("b");
//        fileWriterB.write("c");
//        fileWriterB.close();
//
//        System.out.println(Files.mismatch(Path.of("tmp/a.txt"),Path.of("tmp/b.txt")));
//    }
//
//}
