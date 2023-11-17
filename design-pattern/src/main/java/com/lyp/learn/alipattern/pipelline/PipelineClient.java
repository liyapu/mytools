package com.lyp.learn.alipattern.pipelline;

/**
 * @author liyapu
 * @date 2023-11-17 22:05
 * @description
 */
public class PipelineClient {
    public static void main(String[] args) {

        // 管道初始化
        Pipeline pipeline = new StandardPipeline();

        // value扩展
        PipelineValue graySwitchValuePipeline = new GraySwitchValue();
        PipelineValue forTestValuePipeline = new ForTestValue();

        pipeline.addValue(graySwitchValuePipeline);
        pipeline.addValue(forTestValuePipeline);

        // 上下文
        PipelineContext pipelineContext = new StandardPipelineContext();

        // 调用管道
        pipeline.invoke(pipelineContext);

    }
}
