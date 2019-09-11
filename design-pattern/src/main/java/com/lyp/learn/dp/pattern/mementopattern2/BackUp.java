package com.lyp.learn.dp.pattern.mementopattern2;

/**
 * 备份信息
 */
public class BackUp {
    //版本
    int version;
    //备份内容
    String content;

    public BackUp(String content){
        this.content = content;
    }
}
