package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:03
 * @description
 */
public abstract class AbstractPipelineValue implements PipelineValue {

    @Override
    public boolean execute(PipelineContext pipelineContext) {

        System.out.println(this.getClass().getSimpleName() + " start ");

        boolean result = doExec(pipelineContext);

        System.out.println(this.getClass().getSimpleName() + " end ");

        return result;
    }

    protected abstract boolean doExec(PipelineContext pipelineContext);
}