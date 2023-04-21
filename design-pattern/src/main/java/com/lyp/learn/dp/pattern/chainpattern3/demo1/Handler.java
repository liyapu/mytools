package com.lyp.learn.dp.pattern.chainpattern3.demo1;

/**
 * @author liyapu
 * @date 2023-04-20 20:02
 * @description 基于连表实现
 */
public abstract class Handler {

    /**
     * 下一个处理器
     */
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 入口方法
     * 自身调用自身，循环调用
     */
    public final void handle() {
        boolean handled = doHandle();
        if (successor != null && !handled) {
            successor.handle();
        }
    }

    /**
     * 不同过滤器去实现
     *
     * @return
     */
    protected abstract boolean doHandle();
}
