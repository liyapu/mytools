package com.lyp.learn.dp.pattern.composite3;

/**
 * @author liyapu
 * @date 2023-03-18 20:45
 * @description 文件
 */
public class File extends FileSystemNode {

    public File(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            return 0;
        }
        return file.length();
    }
}