package com.lyp.learn.dp.pattern.statepattern3;

import com.google.common.collect.HashBasedTable;
import java.util.Objects;

/**
 * @author liyapu
 * @date 2022-09-21 08:33
 * @description
 */
public class StateMachine {

    private static HashBasedTable<State, Event, State> basedTable = HashBasedTable.create();

    static {
        basedTable.put(State.UN_SUBMIT, Event.SUBMIT, State.LEADER_CHECK);
        basedTable.put(State.LEADER_CHECK, Event.PASS, State.HR_CHECK);
        //领导的拒绝
        basedTable.put(State.UN_SUBMIT, Event.REJECT, State.REJECT);
        basedTable.put(State.HR_CHECK, Event.PASS, State.FINISH);
        //HR的拒绝，无法区分 领导和HR的拒绝，这两个人拒绝，可能后续有不同的动作
        basedTable.put(State.UN_SUBMIT, Event.REJECT, State.REJECT);
    }

    public static State getNext(State state, Event event) {
        State result = basedTable.get(state, event);
        if (Objects.isNull(result)) {
            throw new IllegalArgumentException("未找到状态");
        }
        return result;
    }
}











