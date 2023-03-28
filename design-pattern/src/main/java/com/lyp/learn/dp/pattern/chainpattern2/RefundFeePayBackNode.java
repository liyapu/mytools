package com.lyp.learn.dp.pattern.chainpattern2;


/**
 * @author liyapu
 * @date 2021-08-05 19:46
 * @desc
 */
public class RefundFeePayBackNode extends RefundResponsibilityChainNode {
    protected RefundFeePayBackNode(ChainStrategy chainStrategy) {
        super(chainStrategy);
    }

    @Override
    protected boolean canHandle(RefundRequest refundRequest) {
        return true;
    }

    @Override
    protected void doHandle(RefundRequest refundRequest) {
        System.out.println("退还余额");
    }
}
