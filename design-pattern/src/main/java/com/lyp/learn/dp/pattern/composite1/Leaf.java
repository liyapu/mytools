package com.lyp.learn.dp.pattern.composite1;

/**
 * 叶子构件
 */
public class Leaf implements Component {
    //叶子对象名字
    private String name;

    public Leaf(String name){
        this.name = name;
    }

    /**
     * 输出叶子对象的结构
     * 叶子对象没有子对象，也就是输出叶子对象的名字
     */
    @Override
    public void printStruct() {
        System.out.println(this.name);
    }
}
