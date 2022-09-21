package com.lyp.learn.dp.pattern.statepattern4;

import com.google.common.collect.HashBasedTable;
import java.util.List;
import java.util.Objects;
import lombok.Data;

/**
 * @author liyapu
 * @date 2022-09-21 08:45
 * @description
 */
public abstract class AbstractStateMachine {

    private HashBasedTable<State, Event, SopExec> hashBasedTable = HashBasedTable.create();

    @Data
    static class SopExec {

        private State nextState;
        private IStateHandle stateHandle;
    }

    {
        final List<SopProcess> sopProcessList = init();
        sopProcessList.forEach(item -> {
            SopExec sopExec = new SopExec();
            sopExec.setNextState(item.getTo());
            sopExec.setStateHandle(item.getStateHandle());

            hashBasedTable.put(item.getFrom(), item.getEvent(), sopExec);
        });
    }


    abstract List<SopProcess> init();

    public State getNext(State state, Event event) {
        final SopExec sopExec = hashBasedTable.get(state, event);
        if (Objects.isNull(sopExec)) {
            throw new IllegalArgumentException("未找到状态");
        }
        return sopExec.getNextState();
    }

    public IStateHandle getHandle(State state, Event event) {
        final SopExec sopExec = hashBasedTable.get(state, event);
        if (Objects.isNull(sopExec)) {
            throw new IllegalArgumentException("未找到处理器");
        }
        return sopExec.getStateHandle();
    }

    public SopExec getSopExec(State state, Event event) {
        final SopExec sopExec = hashBasedTable.get(state, event);
        if (Objects.isNull(sopExec)) {
            throw new IllegalArgumentException("未找到状态处理器");
        }
        return sopExec;
    }
}
