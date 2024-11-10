package com.lyp.learn.others;

/**
 * @author liyapu
 * @date 2024-11-08 16:20
 * @description
 */
public class SkuInfo {

    private Integer skuId;
    private Integer hour;


    public SkuInfo(Integer skuId, Integer hour) {
        this.skuId = skuId;
        this.hour = hour;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "SkuInfo{" +
                "skuId=" + skuId +
                ", hour=" + hour +
                '}';
    }
}
