package com.lyp.learn.dp.pattern.chainpattern2;

/**
 * @author liyapu
 * @date 2021-08-05 19:29
 * @desc
 */
public  class RefundRequest {
    protected final RefundType refundType;
    protected final RefundCommissionType refundCommissionType;

    public RefundRequest(
            RefundType refundType, RefundCommissionType refundCommissionType) {
        this.refundType = refundType;
        this.refundCommissionType = refundCommissionType;
    }
}
