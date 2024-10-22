package com.lyp.learn.unit.enums;

public enum WeightUnitEnum {
    /**
     * mg
     */
    MG(1L),
    /**
     * g
     */
    G(101L),
    /**
     * kg
     */
    KG(102L);

    private long code;

    private WeightUnitEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return this.code;
    }
}