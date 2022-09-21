package com.lyp.learn.dp.pattern.statepattern4;

/**
 * @author liyapu
 * @date 2022-09-21 09:05
 * @description
 */
public class LeaderPassHandle implements IStateHandle<String, String> {

    @Override
    public String handle(String s) {
        System.out.println("收到了消息：" + s);
        return "领导业务处理完成";
    }
}
