package com.lyp.learn.dp.pattern.statepattern;

/**
 *  具体状态1
 */
public class ConcreteState1 extends State {

    @Override
    public void handle() {
        System.out.println("行为1 的逻辑处理");
    }
}
