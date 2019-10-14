package com.lyp.learn.guava.io;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {

//    public static final String SOURCE_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\source.txt";
    public static final String SOURCE_FILE =  Paths.get("","src","main","resources","io","sources.txt").toAbsolutePath().toString();
//    public static final String TARGET_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\target.txt";
    public static final String TARGET_FILE =  Paths.get("","src","main","resources","io","target.txt").toAbsolutePath().toString();
    public static final String MOVE_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\move.txt";
    public static final String WRITE_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\write.txt";
    public static final String TOUCH_FILE = "D:\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\touch_new.txt";

    /**
     * 复制文件
     */
    @Test
    public void testCopyFile() throws IOException {
        Files.copy(new File(SOURCE_FILE),new File(TARGET_FILE));
    }

    /**
     * 移动文件
     */
    @Test
    public void testMoveFile() throws IOException {
        Files.move(new File(TARGET_FILE),new File(MOVE_FILE));
    }

    String TEST_DIR = "D:\\temp\\new\\one\\two\\three\\new.txt";
    /**
     * 创建父目录
     * 不会创建文件
     */
    @Test
    public void testCreateParentDir() throws IOException {
        Files.createParentDirs(new File(TEST_DIR));
    }

    /**
     * 读取整个文件
     */
    @Test
    public void testReadLines() throws IOException {
        List<String> lines = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        for(String line : lines){
            System.out.println(line);
        }
    }
    /**
     * 读取文件的所有行，并且每行都用processor处理
     *
     * 可以看readLines 源码，processLine 返回false,就停止读取
     */
    @Test
    public void test() throws IOException {
        LineProcessor<List<Integer>> listLineProcessor = new LineProcessor<List<Integer>>() {
            List<Integer> lineList = new ArrayList<>();
            @Override
            public boolean processLine(String line) throws IOException {
                lineList.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return lineList;
            }
        };

        final List<Integer> result = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8).readLines(listLineProcessor);
        System.out.println(result);
    }

    /**
     * 读取文件到一个字符串中
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        String read = Files.asCharSource(new File(SOURCE_FILE), Charsets.UTF_8).read();
        System.out.println(read);
    }
    /**
     * 对文件做 hash,得到一个文件hash码，
     * 后续可以用于判断文件是否被篡改，或者文件是否修改了
     */
    @Test
    public void testFileMd5() throws IOException {
        HashCode hash = Files.asByteSource(new File(SOURCE_FILE)).hash(Hashing.sha256());
        System.out.println(hash.asInt());
        System.out.println(hash.toString());
    }

    @Test
    public void testFileName(){
        File file = new File(SOURCE_FILE);
        System.out.println(file.getName());
        System.out.println(Files.getFileExtension(file.toString()));
        System.out.println(Files.getNameWithoutExtension(file.toString()));
    }

    /**
     * 写入文件
     */
    @Test
    public void testWrite() throws IOException {
        File file = new File(WRITE_FILE);
        String content1 = " 写入文本1";
        Files.write(content1.getBytes(),file);

        //把第一次写入的覆盖掉了
        String content2 = "写入文本的 内容2";
        Files.write(content2.getBytes(),file);
    }

    /**
     * append
     * 文件追加的方式写入
     */
    @Test
    public void testAppend() throws IOException {
        File toFile = new File(WRITE_FILE);

        String content1 = " 写入文本1\r\n";
        Files.asCharSink(toFile,Charsets.UTF_8,FileWriteMode.APPEND).write(content1);

        String content2 = "写入文本的 内容2\r\n";
        Files.asCharSink(toFile,Charsets.UTF_8,FileWriteMode.APPEND).write(content2);
    }

    /**
     * 创建空文件
     */
    @Test
    public void testTouch() throws IOException {
        Files.touch(new File(TOUCH_FILE));
    }

    @Test
    public void testRecursiveFile(){
        File root = new File("D:\\myGitRepository\\mytools\\open-tools\\src\\main");
        List<File> list = new ArrayList<>();
        recursiveFile(root,list);
        list.forEach(System.out::println);
    }
    /**
     * 递归获取文件夹下的所有文件
     * @param root
     * @param list
     */
    public void recursiveFile(File root,List<File> list){
        if(root.isHidden()){
            return;
        }
        list.add(root);
        if(root.isDirectory()){
            File[] files = root.listFiles();
            for(File f : files){
                recursiveFile(f,list);
            }
        }
    }

    @Test
    public void testRecursiveFileName(){
        File root = new File("D:\\myGitRepository\\mytools\\open-tools\\src\\main");
        List<String> list = new ArrayList<>();
        recursiveFileName(root,list);
        list.forEach(System.out::println);
    }
    /**
     * 递归获取文件夹下的所有文件的文件名字
     * @param root
     * @param list
     */
    public void recursiveFileName(File root,List<String> list){
        if(root.isHidden()){
            return;
        }
        list.add(root.getName());
        if(root.isDirectory()){
            File[] files = root.listFiles();
            for(File f : files){
                recursiveFileName(f,list);
            }
        }
    }

    @Test
    public void testDepthFirstPreOrder(){
        File root = new File("D:\\myGitRepository\\mytools\\open-tools\\src\\main");
        Iterable<File> files = Files.fileTraverser().depthFirstPreOrder(root);
        files.forEach(System.out::println);
    }


    @Test
    public void testBreadthFirst(){
        File root = new File("D:\\myGitRepository\\mytools\\open-tools\\src\\main");
        Iterable<File> files = Files.fileTraverser().breadthFirst(root);
        files.forEach(System.out::println);
    }
}
