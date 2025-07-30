package com.lyp.learn.apachecommons.io;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-30 18:55
 */
public class FileUtilsTest {

    public static String inDirStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/out";
    public static String outDirStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/out";
    public static String fileStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/source.txt";
    public static String tempDir ="/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp";

    /**
     * 创建文件
     */
    @Test
    public void testCreate() throws IOException {
        File fileDir = new File(inDirStr);

        //从文件夹中获取指定文件
        File file = FileUtils.getFile(fileDir,"source.txt");

        //获取临时文件目录
        File tempFile = FileUtils.getTempDirectory();
        System.out.println(tempFile.isDirectory());
        System.out.println("tempFile :" + tempFile.toString()) ;

        //获取临时文件路径
        String tempFilePath = FileUtils.getTempDirectoryPath();
        System.out.println("tempFilePath :" + tempFilePath) ;

        //获取用户文件夹
        File userFileDir = FileUtils.getUserDirectory();
        System.out.println("userFileDir :" + userFileDir.toString()) ;

        //获取用户文件路径
        String userFilePath = FileUtils.getUserDirectoryPath();
        System.out.println("userFilePath :" + userFilePath) ;

        //强制创建文件夹
        FileUtils.forceMkdir(fileDir);

        //强制创建父文件夹
        FileUtils.forceMkdir(fileDir);
    }


    /**
     * 删除
     */
    @Test
    public void testDelete() throws IOException {
        String tempDir1 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp1";
        String tempDir2 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp2";
        String tempDir3 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp3";
        String tempDir4 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp4";
        String tempDir5 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp5";
        String tempDir6 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp6";

        //强制删除，会抛出异常，目录本身也会被删除
//        FileUtils.deleteDirectory(new File(tempDir1));

        //删除目录 会抛出异常，目录本身也会被删除
//        FileUtils.deleteDirectory(new File(tempDir2));

        //温和的删除目录，不把抛出异常 目录本身也会被删除
//        FileUtils.deleteQuietly(new File(tempDir3));

        //清空目录中的所有文件夹和文件，目录本身不变
        FileUtils.cleanDirectory(new File(tempDir4));

        //jvm退出时强制删除文件
//        FileUtils.forceDeleteOnExit(new File(tempDir5));
    }

    /**
     * 修改
     */
    @Test
    public void testMove() throws IOException {
        File sourceFile = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/move.txt");
        File targetFile = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/move2.txt");

        File sourceDir = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp7");
        File targetDir = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/temp8");

        //移动文件
//        FileUtils.moveFile(sourceFile,targetFile);
        //移动文件夹
//        FileUtils.moveDirectory(sourceDir,targetDir);

        //移动文件到文件夹中
//        FileUtils.moveFileToDirectory(targetFile,targetDir,true);

        //移动文件或文件夹到指定文件夹
        FileUtils.moveToDirectory(targetFile,targetDir,true);
    }

    /**
     * 查找
     */
    @Test
    public void testFind(){
        //是否递归查找指定扩展名的文件
        String [] extensions = new String[]{"png","java"};
        File sourceDir = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io");
        Collection<File> files = FileUtils.listFiles(sourceDir, extensions, true);
        files.stream()
                .forEach(System.out::println);
    }

    @Test
    public void testFind1(){
        File sourceDir = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io");
        String[] moves = sourceDir.list(FileFilterUtils.nameFileFilter("taiji.png"));
        for(String s : moves){
            System.out.println(s);
        }
    }

    /**
     * 读取文件
     */
    @Test
    public void testRead() throws IOException {
        File sourFile = new File(fileStr);
        //根据文件获取文件输入流
        FileInputStream fileInputStream = FileUtils.openInputStream(sourFile);

        //读取文件到字节数组
        byte[] bytes = FileUtils.readFileToByteArray(sourFile);
        String str1 = new String(bytes);
        System.out.println("str1=" + str1);

        //读取文件到字符串
        String s1 = FileUtils.readFileToString(sourFile, "UTF-8");
        System.out.println("s1=" + s1);

        String s2 = FileUtils.readFileToString(sourFile, Charsets.UTF_8);
        System.out.println("s2 =" + s2);

        //读取文件到集合中
        List<String> list1 = FileUtils.readLines(sourFile, "UTF-8");
        list1.stream()
                .forEach(System.out::println);
        List<String> list2 = FileUtils.readLines(sourFile, Charsets.UTF_8);
        list2.stream()
                .forEach(System.out::println);

        //获取文件大小
        long size = FileUtils.sizeOf(sourFile);
        System.out.println("size=" + size);
    }

    /**
     * 写文件
     */
    @Test
    public void testWrite() throws IOException {
        File sourFile = new File(fileStr);
        File outFile = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write2.txt");
        //根据文件获取输出流
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(outFile);
        //获取文件输出流并指定是否追加到文件中
        FileOutputStream fileOutputStream1 = FileUtils.openOutputStream(outFile, true);

        //将字节数组内容写到文件文件中，文件不存在时创建
        byte[] bytes = FileUtils.readFileToByteArray(sourFile);
        FileUtils.writeByteArrayToFile(outFile,bytes);

        //将字节数组内容写入到文件中，文件不存在时创建，并指定是否追加
        FileUtils.writeByteArrayToFile(outFile,bytes,true);

        //将集合数据按行写入到文件中，并指定编码和是否以追加的方式写
        List<String> list1 = FileUtils.readLines(sourFile, "UTF-8");
        FileUtils.writeLines(outFile,list1);
        FileUtils.writeLines(outFile,list1,true);
        FileUtils.writeLines(outFile,"UTF-8",list1,"true");

        //将字符串数据写入到文件中，并指定编码和是否以追加的方式写
        String str1 = FileUtils.readFileToString(sourFile, "UTF-8");
        FileUtils.writeStringToFile(outFile,str1,"UTF-8");
        FileUtils.writeStringToFile(outFile,str1,"UTF-8",true);
    }

    /**
     * 复制文件
     */
    @Test
    public void testCopy() throws IOException {
        File fileDir = new File(tempDir);
        File destDir = new File(outDirStr);

        //复制文件目录中的内容到另一个目录
//        FileUtils.copyDirectory(fileDir,destDir);
        //复制文件目录，并指定是否保存文件日期
//        FileUtils.copyDirectory(fileDir,destDir,true);
        //使用文件过滤器过滤文件
//        FileUtils.copyDirectory(fileDir,destDir,new NameFileFilter("move.txt"));

        //复制文件
        File sourceFile1 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write.txt");
        File sourceFile2 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write3.txt");
//        FileUtils.copyFile(sourceFile1,sourceFile2);
        //复制文件并指定是否指定保留文件日期
//        FileUtils.copyFile(sourceFile1,sourceFile2,true);


        //复制文件到目录
//        FileUtils.copyFileToDirectory(sourceFile1,destDir);

        //将Url资源复制到指定文件
//        FileUtils.copyURLToFile(new URL(""),sourceFile2);

        //复制输入流到指定文件
        FileInputStream fileInputStream = FileUtils.openInputStream(sourceFile1);
        FileUtils.copyInputStreamToFile(fileInputStream,sourceFile2);
    }

    /**
     * 文件过滤
     */
    @Test
    public void testCompare() throws IOException {
        File sourceFile1 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write.txt");
        File sourceFile2 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write3.txt");

        //比较文件内容是否相同
        boolean b1 = FileUtils.contentEquals(sourceFile1, sourceFile2);
        System.out.println("b1 = " + b1);
        boolean b2 = FileUtils.contentEqualsIgnoreEOL(sourceFile1, sourceFile2, "UTF-8");
        System.out.println("b2 = " + b2);

        //判断一个文件夹是否包含另一个文件夹
        File fileDir = new File(tempDir);
        File destDir = new File(outDirStr);
        boolean b3 = FileUtils.directoryContains(fileDir, destDir);
        System.out.println("b3 =" + b3);

        //在指定的日期判断文件是否是新文件
        boolean fileNewer = FileUtils.isFileNewer(sourceFile1, new Date());
        boolean fileNewer1 = FileUtils.isFileNewer(sourceFile1, sourceFile2);
        boolean fileOlder = FileUtils.isFileOlder(sourceFile1, new Date());
        boolean fileOlder1 = FileUtils.isFileOlder(sourceFile1, sourceFile2);
    }

    /**
     * 文件操作的数据转换
     */
    @Test
    public void testTransfer() throws IOException {
        List<File> filList = new ArrayList<>();
        File sourceFile1 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write.txt");
        File sourceFile2 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write3.txt");
        filList.add(sourceFile1);
        filList.add(sourceFile2);

        //文件集合转文件数组
        File[] fileArr = FileUtils.convertFileCollectionToFileArray(filList);

        //将url资源转换为文件对象
        FileUtils.toFile(new URL(""));
        //将多个URL资源转换为文件对象
        URL[] urls = new URL[]{};
        File[] fileArr2 = FileUtils.toFiles(urls);

        //将文件数组转换为URL资源
        URL[] urls1 = FileUtils.toURLs(fileArr);
    }

    /**
     * 文件迭代
     */
    @Test
    public void testIterator() throws IOException {
        File sourceFile1 = new File("/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/write.txt");
        LineIterator li = FileUtils.lineIterator(sourceFile1, "UTF-8");
        while (li.hasNext()) {
//            System.out.println(li.nextLine());
            System.out.println(li.next());
        }
    }


    /**
     * 读取文件
     */
    @Test
    public void testRead2() throws IOException {
        String strPath = "/Users/liyapu/mywork/repositoryMy/mytools/open-tools/src/main/resources/老链路退供单.txt";
        File sourFile = new File(strPath);
        //根据文件获取文件输入流

        //读取文件到集合中
        List<String> strList = FileUtils.readLines(sourFile, "UTF-8");
        Set<String> set = new HashSet<>();

        //strList.stream()
        //    .forEach(System.out::println);

        int len = "YXTH202203086619528".length();
        for (String s : strList) {
            if (s.contains("YXTH")) {
                int start = s.indexOf("YXTH");
                String no = s.substring(start, start + len);
                set.add(no);
            }
        }
        //set.stream().forEach(System.out::println);
        StringBuffer sb = new StringBuffer();
        for (String s : set) {
            sb.append("'").append(s).append("',");
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testOutNo() throws IOException {
        String fileStr = "/Users/liyapu/mywork/repositoryMy/mytools/open-tools/src/main/resources/deadOutNo.txt";
        File sourceFile = new File(fileStr);
        List<String> lineList = FileUtils.readLines(sourceFile, "UTF-8");
        List<String> result = Lists.newArrayList();
        for (String line : lineList) {
            int startIndex = line.indexOf("SJWLCK");
            if (startIndex <= 0) {
                continue;
            }
            if (!line.contains("PICK_UP_START")) {
                continue;
            }
            String outNo = line.substring(startIndex, startIndex + "SJWLCK2022052401879919".length());
            if (result.contains(outNo)) {
                continue;
            }
            result.add(outNo);
            System.out.println("\"" + outNo + "\",");
        }
    }

    @Test
    public void testOut3() throws IOException {
        String fileStr = "/Users/liyapu/mywork/repositoryMy/mytools/open-tools/src/main/resources/deadOutNo.txt";
        File sourceFile = new File(fileStr);
        List<String> lineList = FileUtils.readLines(sourceFile, "UTF-8");
        List<String> result = Lists.newArrayList();
        for (String line : lineList) {
            int startIndex = line.indexOf("SJWLCK");
            if (startIndex <= 0) {
                continue;
            }
            if (!line.contains("PICK_UP_START")) {
                continue;
            }
            String outNo = line.substring(startIndex, startIndex + "SJWLCK2022052401879919".length());
            if (result.contains(outNo)) {
                continue;
            }
            result.add(outNo);
            System.out.println("\"" + outNo + "\",");
        }
    }

}
