package com.lyp.learn.dp.pattern.mementopattern1;

/**
 * 备忘录角色
 */
public class Memento {
    //发起人的内部状态
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
