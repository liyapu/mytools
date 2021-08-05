package com.lyp.learn.dp.pattern.chainpattern2;

import com.lyp.learn.dp.pattern.chainpattern2.ChainStrategy;
import com.lyp.learn.dp.pattern.chainpattern2.RefundRequest;
import com.lyp.learn.dp.pattern.chainpattern2.RefundResponsibilityChainNode;

/**
 * @author liyapu
 * @date 2021-08-05 19:48
 * @desc
 */
public class FinancialAccountingNode extends RefundResponsibilityChainNode {
    protected FinancialAccountingNode(ChainStrategy chainStrategy) {
        super(chainStrategy);
    }

    @Override
    protected boolean canHandle(RefundRequest refundRequest) {
        return true;
    }

    @Override
    protected void doHandle(RefundRequest refundRequest) {
        System.out.println("执行财务记账");
    }
}
