package com.lyp.learn.apachecommons.io;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-30 19:46
 */
public class FilenameUtilsTest {
    /**
     * 判断父目录是否包含子元素(文件或目录)
     */
    @Test
    public void testDirectoryContains() throws IOException {
        String parent = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources";
        String son1 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io";
        String son2 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/source.txt";
        System.out.println(FilenameUtils.directoryContains(parent,son1));
        System.out.println(FilenameUtils.directoryContains(parent,son2));
    }


    /**
     * 判断两个文件名是否相等
     */
    @Test
    public void testEquals() throws IOException {
        String parent = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources";
        String son1 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io";
        String son2 = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/source.txt";
        System.out.println(FilenameUtils.equals(parent,son1));
        System.out.println(FilenameUtils.equals(parent,son2));
        System.out.println(FilenameUtils.equals(son2,son2));
        System.out.println();
        System.out.println(FilenameUtils.equalsNormalized(parent,son1));
        System.out.println(FilenameUtils.equalsNormalized(parent,son2));
        System.out.println(FilenameUtils.equalsNormalized(son2,son2));
    }

    @Test
    public void testBase(){
        String filePathStr = "/Users/liyapu/myGitRepository/mytools/open-tools/src/main/resources/io/source.txt";
        String filePathStrWindows = "C:\\liyapu\\myGitRepository\\mytools\\open-tools\\src\\main\\resources\\io\\source.txt";

        //获取文件基本名
        System.out.println(FilenameUtils.getBaseName(filePathStr));

        //获取文件扩展名
        System.out.println(FilenameUtils.getExtension(filePathStr));

        //获取文件完整路径，不好文件文件名
        System.out.println(FilenameUtils.getFullPath(filePathStr));

        //获取文件不含结尾分隔符的完整路径，不含文件文件名
        System.out.println(FilenameUtils.getFullPathNoEndSeparator(filePathStr));

        //获取单独的文件名和后缀
        System.out.println(FilenameUtils.getName(filePathStr));

        //获取不含前缀的完整文件路径，不含文件名
        System.out.println(FilenameUtils.getPath(filePathStr));

        //获取不含前缀和结尾分隔符的完整文件路径，不含文件名
        System.out.println(FilenameUtils.getPathNoEndSeparator(filePathStr));

        //获取文件路径前缀
        System.out.println(FilenameUtils.getPrefix(filePathStr));

        //获取文件路径前缀
        System.out.println(FilenameUtils.getPrefixLength(filePathStr));

        //获取文件扩展索引
        System.out.println(FilenameUtils.indexOfExtension(filePathStr));

        //获取文件最后一个分隔符索引
        System.out.println(FilenameUtils.indexOfLastSeparator(filePathStr));

        //判断文件扩展名是否符合给定的
        List<String> suffixList = new ArrayList<>();
        suffixList.add("txt");
        suffixList.add("png");

        System.out.println(FilenameUtils.isExtension(filePathStr,"xlsx"));
        System.out.println(FilenameUtils.isExtension(filePathStr,suffixList));

        //标准化文件路径
        System.out.println(FilenameUtils.normalize(filePathStrWindows));
        //标准化文件路径不含最后的结尾分隔符
        System.out.println(FilenameUtils.normalizeNoEndSeparator(filePathStrWindows));

        //获取不含后缀的文件路径和文件名
        System.out.println(FilenameUtils.removeExtension(filePathStr));


        //转换文件分隔符为系统分隔符
        System.out.println(FilenameUtils.separatorsToUnix(filePathStr));
        System.out.println(FilenameUtils.separatorsToWindows(filePathStr));
        System.out.println(FilenameUtils.separatorsToSystem(filePathStr));


        //判断文件是否符合指定的规则
        System.out.println(FilenameUtils.wildcardMatch(filePathStr,"*.png"));
    }

}
