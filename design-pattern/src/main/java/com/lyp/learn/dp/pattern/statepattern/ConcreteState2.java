package com.lyp.learn.dp.pattern.statepattern;

/**
 *  具体状态2
 */
public class ConcreteState2 extends State {

    @Override
    public void handle() {
        System.out.println("行为2 的逻辑处理");
    }
}
