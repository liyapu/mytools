package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:03
 * @description
 */
public interface PipelineValue {
    /**
     * 节点执行
     *
     * @param pipelineContext
     * @return
     */
    boolean execute(PipelineContext pipelineContext);
}
