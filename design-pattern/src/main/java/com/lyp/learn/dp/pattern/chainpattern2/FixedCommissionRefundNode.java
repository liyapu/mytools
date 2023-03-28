package com.lyp.learn.dp.pattern.chainpattern2;


/**
 * @author liyapu
 * @date 2021-08-05 19:44
 * @desc
 */
public class FixedCommissionRefundNode  extends RefundResponsibilityChainNode {
    protected FixedCommissionRefundNode(ChainStrategy chainStrategy) {
        super(chainStrategy);
    }

    @Override
    protected boolean canHandle(RefundRequest refundRequest) {
        return RefundCommissionType.FIXED == refundRequest.refundCommissionType;
    }

    @Override
    protected void doHandle(RefundRequest refundRequest) {
        System.out.println("按固定额度收取手续费");
    }
}
