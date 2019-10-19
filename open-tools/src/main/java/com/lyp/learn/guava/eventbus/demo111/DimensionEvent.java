package com.lyp.learn.guava.eventbus.demo111;

import java.util.List;

/**
 * @author: liyapu
 * @description: 自定义事件内容
 * @date 2019-10-16 15:04
 */
public class DimensionEvent {
    private int activityId;
    private List<Long> productIds;
    private Long currentTime;
    private Long triedTimes;

    /**
     * @param activityId
     * @param productIds
     * @param currentTime
     * @param triedTimes
     */
    public DimensionEvent(int activityId, List<Long> productIds, Long currentTime, Long triedTimes) {
        super();
        this.activityId = activityId;
        this.productIds = productIds;
        this.currentTime = currentTime;
        this.triedTimes = triedTimes;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Long getTriedTimes() {
        return triedTimes;
    }

    public void setTriedTimes(Long triedTimes) {
        this.triedTimes = triedTimes;
    }

    @Override
    public String toString() {
        return "EventTest DimensionEvent [activityId=" + activityId + ", productIds=" + productIds + ", currentTime=" + currentTime + ", triedTimes=" + triedTimes
                + "]";
    }
}
