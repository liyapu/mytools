package com.lyp.learn.dp.pattern.flyweight1;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 */
public class FlyweightFactory {
    /**
     * 静态的Map来存储享元对象，map集合 称为 池容器
     */
    private static Map<String,Flyweight>  pools = new HashMap<>();

    //私有构造方法
    private FlyweightFactory(){

    }

    /**
     * 根据内部状态，返回享元对象
     * @param intrinsicState
     * @return
     */
    public static Flyweight getFlyweight(String intrinsicState){
        Flyweight flyweight = pools.get(intrinsicState);
        if(flyweight == null){
            System.out.println("创建的内部状态 ： " + intrinsicState);
            flyweight = new ConcreteFlyweight(intrinsicState);
            pools.put(intrinsicState,flyweight);
        }else{
            System.out.println("从池容器中拿到的 ：" + intrinsicState);
        }
        return flyweight;
    }
}
