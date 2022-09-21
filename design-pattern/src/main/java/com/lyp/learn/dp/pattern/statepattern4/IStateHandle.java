package com.lyp.learn.dp.pattern.statepattern4;

/**
 * @author liyapu
 * @date 2022-09-21 09:04
 * @description 状态处理器：将要执行的动作
 */
public interface IStateHandle<T, R> {

    /**
     * 动作处理
     */
    R handle(T t);
}
