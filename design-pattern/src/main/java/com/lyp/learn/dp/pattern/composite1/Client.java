package com.lyp.learn.dp.pattern.composite1;

/**
 * 安全模式（客户端）
 */
public class Client {

    public static void main(String[] args) {
        Composite root = new Composite("D盘");

        Composite favoriteDir = new Composite("收藏夹");

        Composite picDir = new Composite("图片");
        picDir.addChild(new Leaf("美女1.png"));
        picDir.addChild(new Leaf("美女2.png"));

        Composite workDir = new Composite("工作");
        workDir.addChild(new Leaf("需求文档.doc"));

        Composite learningDir = new Composite("学习");
        learningDir.addChild(new Leaf("Java笔记.md"));
        learningDir.addChild(new Leaf("数据库笔记.doc"));

        root.addChild(favoriteDir);
        root.addChild(workDir);
        root.addChild(learningDir);
        root.addChild(picDir);
        root.addChild(new Leaf("log.txt"));

        //错误演示
        //Leaf leaf = new Leaf("test.txt");
        //leaf.addChild(new Leaf("log.txt"))//报错，编译不通过,安全模式下不会出现错误调用

        //输出整个目录结构
        root.printStruct();

    }

}
