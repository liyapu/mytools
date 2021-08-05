package com.lyp.learn.dp.pattern.chainpattern2;

/**
 * @author liyapu
 * @date 2021-08-05 20:04
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("----------开始处理第一个退款------------");
        RefundRequest multiStageAndRateCommissionRequest = new RefundRequest(RefundType.MULTI_STAGE, RefundCommissionType.RATED);
        ProductMode multiProductMode =
                ProductMode.discriminateProductMode(multiStageAndRateCommissionRequest.refundType, multiStageAndRateCommissionRequest.refundCommissionType);
        RefundResponsibilityChainNode multiStageAndRateCommissionChain = RefundResponsibilityChainFactory.createRefundProcessChain(multiProductMode);
        multiStageAndRateCommissionChain.handle(multiStageAndRateCommissionRequest);

        System.out.println();
        System.out.println("----------开始处理第二个退款------------");
        RefundRequest singleStageAndFixedCommissionRequest = new RefundRequest(RefundType.SINGLE_STAGE, RefundCommissionType.FIXED);
        ProductMode singleProductMode = ProductMode.discriminateProductMode(singleStageAndFixedCommissionRequest.refundType, singleStageAndFixedCommissionRequest.refundCommissionType);
        RefundResponsibilityChainNode singleStageAndRatedCommissionChain =
                RefundResponsibilityChainFactory.createRefundProcessChain(singleProductMode);
        singleStageAndRatedCommissionChain.handle(singleStageAndFixedCommissionRequest);
    }
}
