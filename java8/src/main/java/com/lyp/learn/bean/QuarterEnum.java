package com.lyp.learn.bean;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-16 20:16
 */
public enum QuarterEnum {
    Q1TH("q1th", "一季度"),
    Q2ND("q2nd", "二季度"),
    Q3RD("q3rd", "三季度"),
    Q4TH("q4th", "四季度"),
    ;

    private String value;
    private String desc;

    QuarterEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
