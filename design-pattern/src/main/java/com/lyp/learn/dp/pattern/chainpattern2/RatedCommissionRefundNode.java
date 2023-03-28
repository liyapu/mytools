package com.lyp.learn.dp.pattern.chainpattern2;


/**
 * @author liyapu
 * @date 2021-08-05 19:41
 * @desc
 */
public class RatedCommissionRefundNode extends RefundResponsibilityChainNode {

    protected RatedCommissionRefundNode(ChainStrategy chainStrategy) {
        super(chainStrategy);
    }

    @Override
    protected boolean canHandle(RefundRequest refundRequest) {
        return RefundCommissionType.RATED == refundRequest.refundCommissionType;
    }

    @Override
    protected void doHandle(RefundRequest refundRequest) {
        System.out.println("按比例收取手续费");
    }
}
