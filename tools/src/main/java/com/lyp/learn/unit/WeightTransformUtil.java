//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.lyp.learn.unit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.lyp.learn.unit.enums.WeightUnitEnum;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public final class WeightTransformUtil {
    private static final Logger log = LoggerFactory.getLogger(WeightTransformUtil.class);
    private static Map<Long, Long> weightUnitCodeAndMultiplierMap = Maps.newHashMap();

    public static BigDecimal transToKg(long weight, long weightCode) {
        long weightMultiplier = 1000000L;
        Preconditions.checkArgument(weight >= NumberUtils.LONG_ZERO, "weight can't be less than " + NumberUtils.LONG_ZERO);
        Long multiplier = (Long) weightUnitCodeAndMultiplierMap.getOrDefault(weightCode, null);
        Preconditions.checkArgument(Objects.nonNull(multiplier), "未识别的重量单位");
        long transFactor = weightMultiplier / multiplier;
        return BigDecimal.valueOf(weight).divide(BigDecimal.valueOf(transFactor));
    }

    public static BigDecimal transToMg(long weight, long weightCode) {
        long weightMultiplier = 1L;
        Preconditions.checkArgument(weight >= NumberUtils.LONG_ZERO, "weight can't be less than " + NumberUtils.LONG_ZERO);
        Long multiplier = (Long) weightUnitCodeAndMultiplierMap.getOrDefault(weightCode, null);
        Preconditions.checkArgument(Objects.nonNull(multiplier), "未识别的重量单位");
        long transFactor = multiplier / weightMultiplier;
        return BigDecimal.valueOf(weight * transFactor);
    }

    private WeightTransformUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    static {
        weightUnitCodeAndMultiplierMap.put(WeightUnitEnum.MG.getCode(), 1L);
        weightUnitCodeAndMultiplierMap.put(WeightUnitEnum.G.getCode(), 1000L);
        weightUnitCodeAndMultiplierMap.put(WeightUnitEnum.KG.getCode(), 1000000L);
    }
}
