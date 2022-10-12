package com.lyp.learn.base.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liyapu
 * @date 2022-10-12 20:56
 * @description
 */
public enum Month {
    /**
     * 从1月到12月的英语单词依次为
     * January（一月），February（二月），March（三月），April（四月），May（五月），June（六月），July（七月），
     * August（八月），September（九月），October（十月），November（十一月），December（十二月）
     */
    JANUARY(1, "一月"),
    FEBRUARY(2, "二月"),
    MARCH(3, "三月"),
    APRIL(4, "四月"),
    MAY(5, "五月"),
    JUNE(6, "六月"),
    JULY(7, "七月"),
    AUGUST(8, "八月"),
    SEPTEMBER(9, "九月"),
    OCTOBER(10, "十月"),
    NOVEMBER(11, "十一月"),
    DECEMBER(12, "十二月");

    private final int code;
    private final String desc;

    private static final Map<Integer, Month> MONTH_MAP = new HashMap<>();
    private static final Set<Month> SMALL_SET = new HashSet<>();

    Month(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static {
        Arrays.stream(Month.values())
            .forEach(month -> MONTH_MAP.put(month.getCode(), month));

        SMALL_SET.add(JANUARY);
        SMALL_SET.add(FEBRUARY);
        SMALL_SET.add(MARCH);
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isSmallMonth() {
        return SMALL_SET.contains(this);
    }

    public static void main(String[] args) {
        System.out.println(MONTH_MAP.get(1).isSmallMonth());
        System.out.println(MONTH_MAP.get(9).isSmallMonth());
    }

}
