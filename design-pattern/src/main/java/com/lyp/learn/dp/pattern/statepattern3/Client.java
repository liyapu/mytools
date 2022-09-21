package com.lyp.learn.dp.pattern.statepattern3;


/**
 * 客户端
 * <p>
 * 现态 ----> 事件 ----> 动作 ----> 次态
 * `{----------}                  ↑
 * `     ↓-------------------------
 * <p>
 * 未发起--提交-->领导审批中  --同意--> HR审批中--同意-->完成
 * `              ↓ 决绝              ↓拒绝
 * `              --------->拒绝<------
 * <p>
 * <p>
 * bilibili  B站视频教程
 * https://www.bilibili.com/video/BV1A341137FZ/?spm_id_from=333.788.recommend_more_video.3&vd_source=5194da923f0f28ca1a7df9b1380507cd
 */
public class Client {

    public static void main(String[] args) {
        State nextState = StateMachine.getNext(State.LEADER_CHECK, Event.PASS);
        System.out.println(nextState);
    }
}
