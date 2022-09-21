package com.lyp.learn.dp.pattern.statepattern4;

import java.util.Arrays;
import java.util.List;

/**
 * @author liyapu
 * @date 2022-09-21 08:45
 * @description
 */
public class NewStateMachine extends AbstractStateMachine {

    @Override
    List<SopProcess> init() {
        return Arrays.asList(
            SopProcessBuilder.getInstance()
                .from(State.UN_SUBMIT)
                .event(Event.SUBMIT)
                .to(State.LEADER_CHECK)
                .build(),
            SopProcessBuilder.getInstance()
                .from(State.LEADER_CHECK)
                .event(Event.PASS)
                .handle(new LeaderPassHandle())
                .to(State.HR_CHECK)
                .build()
        );
    }

}
