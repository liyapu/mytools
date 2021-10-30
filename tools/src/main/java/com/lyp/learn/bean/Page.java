package com.lyp.learn.bean;


/**
 * Page对象
 */
public class Page {

    /**
     * offset不能小于0
     */
    private int offset;

    /**
     * 分页信息不能为空最大不能超过200
     */
    private int limit;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
