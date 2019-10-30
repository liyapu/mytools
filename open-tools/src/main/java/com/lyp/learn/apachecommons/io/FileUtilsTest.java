package com.lyp.learn.apachecommons.io;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-30 18:55
 */
public class FileUtilsTest {

    public static String inDirStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/out";
    public static String outDirStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/out";
    public static String fileStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/source.txt";

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

    @Test
    public void testFind(){

    }
}
