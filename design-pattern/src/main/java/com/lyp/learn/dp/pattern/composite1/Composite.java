package com.lyp.learn.dp.pattern.composite1;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构建角色类(目录)
 */
public class Composite implements Component {

    //存储组合对象中包含的子组件的对象（可能是目录也可能是文件）
    private List<Component> childCompontents = new ArrayList<>();

    /**
     * 组合对象的名称
     */
    private String name;

    /**
     * 构件名称
     * @param name
     */
    public Composite(String name) {
        this.name = name;
    }

    /**
     * 聚集管理方法：添加一个子构件对象
     * @param child
     */
    public void addChild(Component child){
        childCompontents.add(child);
    }

    /**
     * 聚集管理方法:删除一个子构件对象
     * @param index
     */
    public void removeChild(int index){
        childCompontents.remove(index);
    }

    /**
     * 聚集管理方法: 返回所有子构件对象
     * @return
     */
    public List<Component> getChilds(){
        return childCompontents;
    }


    @Override
    public void printStruct() {
        //先把自己输出
        System.out.println("+" + this.name);
        //如果还包含有子组件，那么就输出这些子组件对象
        List<Component> childs = this.getChilds();
        if(childs != null){
            for(Component c : childs){
                c.printStruct();
            }
        }

    }
}
