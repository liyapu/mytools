package com.lyp.learn.dp.pattern.statepattern5;

/**
 * @author liyapu
 * @date 2023-04-20 21:13
 * @description
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }
}
