package com.lyp.learn.dp.pattern.visitorpattern1;

import java.util.Random;
import java.util.Vector;

/**
 *  结构角色
 */
public class ObjectStructure {

    private Vector<Element> elements;

    public ObjectStructure(){
        elements = new Vector<>();
    }

    //执行访问操作
    public void action(Visitor visitor){
        for(Element e : elements){
            e.accept(visitor);
        }
    }

    //模拟元素生成
    public void createElements(){
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            if(random.nextInt(100) > 50){
                elements.add(new ConcreteElementA());
            }else{
                elements.add(new ConcreteElementB());
            }
        }
    }

    //添加新元素
    public void add(Element element){
        elements.add(element);
    }
}
