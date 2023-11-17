package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:00
 * @description
 */
public interface Pipeline {
    /**
     * 执行
     *
     * @return
     */
    boolean invoke(PipelineContext pipelineContext);

    /**
     * 添加值
     *
     * @param pipelineValue
     * @return
     */
    boolean addValue(PipelineValue pipelineValue);

    /**
     * 移除值
     *
     * @param pipelineValue
     * @return
     */
    boolean removeValue(PipelineValue pipelineValue);
}
