package com.lyp.learn.dp.pattern.flyweight1;

/**
 * 具体享元角色
 */
public class ConcreteFlyweight implements Flyweight {

    //内部状态
    private String intrisicState;

    public ConcreteFlyweight(String intrinsicState){
        this.intrisicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("内部状态 :" + intrisicState);
        System.out.println("外部状态 : " + extrinsicState);
    }
}
