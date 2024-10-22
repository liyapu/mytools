package com.lyp.learn.unit.enums;

/**
 * description：体积单位
 */
public enum VolumeUnitEnum {
    /**
     * 立方厘米
     */
    CUBIC_CENTI_METER(241L),
    /**
     * 立方毫米
     */
    CUBIC_MILLI_METER(242L);

    private long code;

    VolumeUnitEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
