package com.lyp.learn.dp.pattern.chainpattern2;

/**
 * 具体节点定义
 */
public class MultiStageTimeLineExtractRefundNode extends RefundResponsibilityChainNode {

    protected MultiStageTimeLineExtractRefundNode(ChainStrategy chainStrategy) {
        super(chainStrategy);
    }

    @Override
    protected boolean canHandle(RefundRequest refundRequest) {
        return RefundType.MULTI_STAGE ==  refundRequest.refundType;
    }

    @Override
    protected void doHandle(RefundRequest refundRequest) {
        System.out.println("获取手续费预处理阶段");
    }
}
