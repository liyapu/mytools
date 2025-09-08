package com.lyp.learn.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 灰度读阶段枚举
 */
public enum GrayReaderPhaseEnum {
    /**
     * 只读旧
     */
    ONLY_OLD(0),
    /**
     * 同时读新和旧，取旧
     */
    BOTH_OLD_AND_NEW_SELECT_OLD(1),
    /**
     * 同时读新和旧，取新
     */
    BOTH_OLD_AND_NEW_SELECT_NEW(2),
    /**
     * 只读新
     */
    ONLY_NEW(3)
    ;

    private static final Map<Integer, GrayReaderPhaseEnum> CODE_2_ENUM = Stream.of(GrayReaderPhaseEnum.values())
            .collect(Collectors.toMap(GrayReaderPhaseEnum::getCode, item -> item, (v1, v2) -> v2));

    private final int code;

    GrayReaderPhaseEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GrayReaderPhaseEnum of(int code) {
        return CODE_2_ENUM.get(code);
    }


}
