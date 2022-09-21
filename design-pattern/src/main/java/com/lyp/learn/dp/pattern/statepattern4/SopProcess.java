package com.lyp.learn.dp.pattern.statepattern4;

/**
 * @author liyapu
 * @date 2022-09-21 08:49
 * @description SOP处理类
 */
public class SopProcess {

    private State from;
    private State to;
    private Event event;
    private IStateHandle stateHandle;

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public IStateHandle getStateHandle() {
        return stateHandle;
    }

    public void setStateHandle(IStateHandle stateHandle) {
        this.stateHandle = stateHandle;
    }
}

class SopProcessBuilder {

    private SopProcess sopProcess;

    public void setSopProcess(SopProcess sopProcess) {
        this.sopProcess = sopProcess;
    }

    public static SopProcessBuilder getInstance() {
        SopProcessBuilder sopBuilder = new SopProcessBuilder();
        sopBuilder.setSopProcess(new SopProcess());
        return sopBuilder;
    }

    public SopProcessBuilder from(State state) {
        sopProcess.setFrom(state);
        return this;
    }

    public SopProcessBuilder handle(IStateHandle stateHandle) {
        sopProcess.setStateHandle(stateHandle);
        return this;
    }

    public SopProcessBuilder to(State state) {
        sopProcess.setTo(state);
        return this;
    }

    public SopProcessBuilder event(Event event) {
        sopProcess.setEvent(event);
        return this;
    }

    public SopProcess build() {
        return sopProcess;
    }
}
