package com.lyp.learn.dp.pattern.chainpattern2;

/**
 * 退款逻辑复杂：有的完全不允许退款，有的需要提前两天才能退款，有的随时可退
 */
public enum RefundType {
    MULTI_STAGE,
    SINGLE_STAGE;
}
