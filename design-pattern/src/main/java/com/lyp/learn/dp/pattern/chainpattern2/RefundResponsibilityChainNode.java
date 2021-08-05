package com.lyp.learn.dp.pattern.chainpattern2;


/**
 *  责任链节点基类
 */
public abstract class RefundResponsibilityChainNode {
    protected final ChainStrategy chainStrategy;
    protected  RefundResponsibilityChainNode next;

    protected RefundResponsibilityChainNode(ChainStrategy chainStrategy){
        this.chainStrategy = chainStrategy;
    }

    public void setNext(RefundResponsibilityChainNode next){
        this.next = next;
    }

    protected abstract boolean canHandle(RefundRequest refundRequest);

    protected abstract void doHandle(RefundRequest refundRequest);

    public void handle(RefundRequest refundRequest){
        boolean canHandleFlag = canHandle(refundRequest);
        chainStrategy.check(canHandleFlag);

        if(canHandleFlag){
            doHandle(refundRequest);
        }
        if(next != null){
            next.handle(refundRequest);
        }
    }



}
