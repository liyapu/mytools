package com.lyp.learn.dp.pattern.statepattern5;

/**
 * @author liyapu
 * @date 2023-04-20 21:10
 * @description 马里奥
 */
public interface IMario {

    State getName();

    void obtainMushRoom(MarioStateMachine stateMachine);

    void obtainCape(MarioStateMachine stateMachine);

    void obtainFireFlower(MarioStateMachine stateMachine);

    void meetMonster(MarioStateMachine stateMachine);
}
