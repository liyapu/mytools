package com.lyp.learn.dp.pattern.statepattern4;


import com.lyp.learn.dp.pattern.statepattern4.AbstractStateMachine.SopExec;

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
 */
public class Client {

    public static void main(String[] args) {
        NewStateMachine newStateMachine = new NewStateMachine();
        final State nextState = newStateMachine.getNext(State.LEADER_CHECK, Event.PASS);
        System.out.println("nextState--" + nextState);
        System.out.println();

        IStateHandle<String, String> stateHandle = newStateMachine.getHandle(State.LEADER_CHECK, Event.PASS);

        String result = stateHandle.handle("入参内容");
        System.out.printf("result --- " + result);
        System.out.println();

        System.out.println("---------------");
        final SopExec sopExec = newStateMachine.getSopExec(State.LEADER_CHECK, Event.PASS);
        System.out.println(sopExec.getNextState());
        System.out.println(sopExec.getStateHandle().handle("测试方法二"));

    }
}
