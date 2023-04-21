package com.lyp.learn.dp.pattern.statepattern5;

/**
 * @author liyapu
 * @date 2023-04-20 21:13
 * @description
 */
public enum State {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}