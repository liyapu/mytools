package com.lyp.learn.dp.pattern.statepattern;

/**
 *  抽象角色
 */
public abstract class State {
    //持有一个环境对象
    protected  Context context;


    public void setContext(Context context) {
        this.context = context;
    }

    //抽象行为
    public abstract void handle();
}
