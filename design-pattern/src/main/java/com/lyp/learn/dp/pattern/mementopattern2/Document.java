package com.lyp.learn.dp.pattern.mementopattern2;

/**
 * 文档
 */
public class Document {
    String content; //需要备份的状态
    String otherContent; //不需要备份的状态

    //保存一个备份
    public BackUp save(){
        return new BackUp(content);
    }

    //恢复某个状态
    public void resume(BackUp backUp){
        content = backUp.content;
    }

    @Override
    public String toString() {
       return "content: " + content + "，otherContent:" + otherContent;
    }
}
