package com.lyp.learn.dp.pattern.statepattern1;

/**
 *  环境角色
 */
public class Context {

    public State state1 = new ConcreteState1();
    public State state2 = new ConcreteState2();

    //当前状态
    private State state;

    public State getState() {
        return state;
    }

    //设置当前状态
    public void setState(State state) {
        this.state = state;
        //设置状态中的环境
        state.setContext(this);
    }

    //行为1
    public void handle1(){
        setState(state1);
        state.handle();
    }

    //行为2
    public void handle2(){
        setState(state2);
        state.handle();
    }
}
