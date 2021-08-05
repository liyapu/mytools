package com.lyp.learn.dp.pattern.chainpattern2;

/**
 * @author liyapu
 * @date 2021-08-05 19:53
 * @desc
 */
public class RefundResponsibilityChainFactory {
    public static RefundResponsibilityChainNode createRefundProcessChain(ProductMode productMode){
        if(ProductMode.MULTI_STAGE_AND_RATED_COMMISSION == productMode){
            RefundResponsibilityChainNode multiStageTimeLineExtractRefundNode = new MultiStageTimeLineExtractRefundNode(ChainStrategy.REJECT);
            RefundResponsibilityChainNode ratedCommissionRefundNode = new RatedCommissionRefundNode(ChainStrategy.PASS);
            RefundResponsibilityChainNode refundFeePayBackNode = new RefundFeePayBackNode(ChainStrategy.PASS);
            RefundResponsibilityChainNode financialAccountingNode = new FinancialAccountingNode(ChainStrategy.PASS);

            multiStageTimeLineExtractRefundNode.setNext(ratedCommissionRefundNode);
            ratedCommissionRefundNode.setNext(refundFeePayBackNode);
            refundFeePayBackNode.setNext(financialAccountingNode);

            return multiStageTimeLineExtractRefundNode;
        }else if(ProductMode.SINGLE_STAGE_AND_FIXED_COMMISSION == productMode){
            RefundResponsibilityChainNode fixedCommissionRefundNode = new FixedCommissionRefundNode(ChainStrategy.PASS);
            RefundResponsibilityChainNode refundFeePayBackNode = new RefundFeePayBackNode(ChainStrategy.PASS);
            RefundResponsibilityChainNode financialAccountingNode = new FinancialAccountingNode(ChainStrategy.PASS);

            fixedCommissionRefundNode.setNext(refundFeePayBackNode);
            refundFeePayBackNode.setNext(financialAccountingNode);

            return fixedCommissionRefundNode;
        }else {
            throw new IllegalArgumentException();
        }
    }
}
