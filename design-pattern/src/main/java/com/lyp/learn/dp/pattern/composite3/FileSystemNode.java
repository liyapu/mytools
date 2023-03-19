package com.lyp.learn.dp.pattern.composite3;

/**
 * @author liyapu
 * @date 2023-03-18 20:45
 * @description
 */
public abstract class FileSystemNode {

    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}
