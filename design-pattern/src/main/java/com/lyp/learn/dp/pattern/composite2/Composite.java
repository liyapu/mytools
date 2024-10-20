package com.lyp.learn.dp.pattern.composite2;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件角色类，此类将implements Conponent改为extends Conponent，其他地方无变化
 */
public class Composite extends Component {

    /**
     * 用来存储组合对象中包含的子组件对象
     */
    private List<Component> childComponents = new ArrayList<Component>();

    /**
     * 组合对象的名字
     */
    private String name;

    /**
     * 构造方法，传入组合对象的名字
     * @param name    组合对象的名字
     */
    public Composite(String name){
        this.name = name;
    }

    /**
     * 聚集管理方法，增加一个子构件对象
     * @param child 子构件对象
     */
    @Override
    public void addChild(Component child){
        childComponents.add(child);
    }

    /**
     * 聚集管理方法，删除一个子构件对象
     * @param index 子构件对象的下标
     */
    @Override
    public void removeChild(int index){
        childComponents.remove(index);
    }

    /**
     * 聚集管理方法，返回所有子构件对象
     */
    @Override
    public List<Component> getChild(){
        return childComponents;
    }

    /**
     * 输出对象的自身结构
     */
    @Override
    public void printStruct() {
        // 先把自己输出
        System.out.println("+" + this.name);
        //如果还包含有子组件，那么就输出这些子组件对象
        if(this.childComponents != null){
            //输出当前对象的子对象
            for(Component c : childComponents){
                //递归输出每个子对象
                c.printStruct();
            }
        }

    }

}
