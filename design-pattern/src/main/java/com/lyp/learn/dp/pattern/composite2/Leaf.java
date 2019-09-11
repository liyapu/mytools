package com.lyp.learn.dp.pattern.composite2;

/**
 * 树叶构件角色类，此类将implements Conponent改为extends Conponent，其他地方无变化
 */
public class Leaf extends Component {
    /**
     * 叶子对象的名字
     */
    private String name;
    /**
     * 构造方法，传入叶子对象的名称
     * @param name 叶子对象的名字
     */
    public Leaf(String name){
        this.name = name;
    }
    /**
     * 输出叶子对象的结构，叶子对象没有子对象，也就是输出叶子对象的名字
     */
    @Override
    public void printStruct() {
        System.out.println("  - "+ name);
    }

}
