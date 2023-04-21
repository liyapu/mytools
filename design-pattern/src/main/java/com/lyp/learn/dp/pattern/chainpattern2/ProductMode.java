package com.lyp.learn.dp.pattern.chainpattern2;

/**
 * @author liyapu
 * @date 2021-08-05 19:21
 * @desc
 */
public enum ProductMode {
    /**
     * 多步浮动佣金
     */
    MULTI_STAGE_AND_RATED_COMMISSION,
    /**
     * 单步固定佣金
     */
    SINGLE_STAGE_AND_FIXED_COMMISSION;

    public static ProductMode discriminateProductMode(RefundType refundType,
        RefundCommissionType refundCommissionType) {
        if (RefundType.SINGLE_STAGE == refundType && RefundCommissionType.FIXED == refundCommissionType) {
            return SINGLE_STAGE_AND_FIXED_COMMISSION;
        } else if (RefundType.MULTI_STAGE == refundType && RefundCommissionType.RATED == refundCommissionType) {
            return MULTI_STAGE_AND_RATED_COMMISSION;
        }
        throw new IllegalArgumentException();
    }
}
