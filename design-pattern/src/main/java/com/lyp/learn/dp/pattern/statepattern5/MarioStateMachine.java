package com.lyp.learn.dp.pattern.statepattern5;

/**
 * @author liyapu
 * @date 2023-04-20 21:11
 * @description
 */
public class MarioStateMachine {

    private int score;
    private IMario currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    /**
     * 获得蘑菇
     */
    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    /**
     * 获得斗篷
     */
    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    /**
     * 获得火焰
     */
    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    /**
     * 遇到怪兽
     */
    public void meetMonster() {
        this.currentState.meetMonster(this);
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState.getName();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }
}