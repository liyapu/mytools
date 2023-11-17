package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:04
 * @description
 */
public class GraySwitchValue extends AbstractPipelineValue {
    @Override
    public boolean doExec(PipelineContext pipelineContext) {

        pipelineContext.set(PipelineContext.FOR_TEST, true);

        return true;
    }
}

