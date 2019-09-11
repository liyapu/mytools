package com.lyp.learn.base.annotation.complex;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 22:37
 */
public enum TrafficLampEnum {
    RED("红灯"),
    YELLOW("黄灯"),
    GREEN("绿灯");
    TrafficLampEnum(String desc){
        this.desc = desc;
    }

    String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
