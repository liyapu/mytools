package com.lyp.learn.unit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.lyp.learn.unit.enums.VolumeUnitEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * description：商品体积转换工具类
 */
@Slf4j
@UtilityClass
public class VolumeTransformUtil {


    private static Map<Long, Long> volumeUnitCodeAndMultiplierMap = Maps.newHashMap();

    static {
        volumeUnitCodeAndMultiplierMap.put(VolumeUnitEnum.CUBIC_MILLI_METER.getCode(), 1L);
        volumeUnitCodeAndMultiplierMap.put(VolumeUnitEnum.CUBIC_CENTI_METER.getCode(), 1000L);
    }

    /**
     * @param volume     体积
     * @param volumeCode 体积单位代码
     * @return 商品体积（立方米）
     * @description: 统一转换商品体积单位为立方米
     */
    public static BigDecimal transToCubicMeter(long volume, long volumeCode) {
        long meterMultiplier = 1000000000L;
        Preconditions.checkArgument(volume >= NumberUtils.LONG_ZERO,
                "volume can't be less than " + NumberUtils.LONG_ZERO);
        Long multiplier = volumeUnitCodeAndMultiplierMap.getOrDefault(volumeCode, null);
        Preconditions.checkArgument(Objects.nonNull(multiplier), "未识别的体积单位");
        long transFactor = meterMultiplier / multiplier;
        return BigDecimal.valueOf(volume).divide(BigDecimal.valueOf(transFactor));
    }

    /**
     * @param volume     体积
     * @param volumeCode 体积单位代码
     * @description: 统一转换商品体积单位为立方毫米
     * @return: 商品体积（立方毫米）
     */
    public static BigDecimal transToCubicMilliMeter(long volume, long volumeCode) {
        long milliMeterMultiplier = 1L;
        Preconditions.checkArgument(volume >= NumberUtils.LONG_ZERO,
                "volume can't be less than " + NumberUtils.LONG_ZERO);
        Long multiplier = volumeUnitCodeAndMultiplierMap.getOrDefault(volumeCode, null);
        Preconditions.checkArgument(Objects.nonNull(multiplier), "未识别的体积单位");
        long transFactor = multiplier / milliMeterMultiplier;
        return BigDecimal.valueOf(volume * transFactor);
    }
}
