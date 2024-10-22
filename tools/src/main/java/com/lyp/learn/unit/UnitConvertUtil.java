package com.lyp.learn.unit;

import com.lyp.learn.unit.enums.VolumeUnitEnum;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author liyapu
 * @date 2024-09-29 11:52
 * @description 单位转换工具类
 */
public class UnitConvertUtil {

    /**
     * 钱单位转换 分转换为元 进制值
     */
    private static final String FEN_YUAN_BASE_UNIT = "100";
    private static final BigDecimal FEN_YUAN_UNIT = new BigDecimal(FEN_YUAN_BASE_UNIT);

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    private UnitConvertUtil() {
    }

    /**
     * 钱单位转换 元转换为分
     * 示例: 12.34 -> 1234
     *
     * @param yuanStr 以元为单位的字符串数字
     * @return 分
     */
    public static int yuanToFen(String yuanStr) {
        return new BigDecimal(yuanStr).multiply(FEN_YUAN_UNIT).intValue();
    }

    /**
     * 钱单位转换 分转换为元   四舍五入,保留两位小数,并去除最末尾的0
     * 示例: 1234 -> 12.34
     *
     * @param fen 以分为单位的整数
     * @return 以元为单位的字符串数字
     */
    public static String fenToYuan(Integer fen) {
        if (Objects.isNull(fen) || fen == 0) {
            return StringUtils.EMPTY;
        }
        return new BigDecimal(fen).divide(FEN_YUAN_UNIT, 2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String fenToYuan(Long fen) {
        if (Objects.isNull(fen) || fen == 0L) {
            return StringUtils.EMPTY;
        }
        return new BigDecimal(fen).divide(FEN_YUAN_UNIT, 2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 将numStr转成数字,并放大100倍后返回
     * 比如 0.7 -> 70
     *
     * @param numStr 数字字符串
     * @return 放大100倍后的数字
     */
    public static Integer expandOneHundred(String numStr) {
        if (StringUtils.isBlank(numStr)) {
            return null;
        }
        return new BigDecimal(numStr).multiply(ONE_HUNDRED).intValue();
    }

    /**
     * 缩小100倍后返回
     * 比如 70 -> 0.7
     *
     * @param num
     * @return
     */
    public static String shrinkOneHundred(Integer num) {
        if (Objects.isNull(num)) {
            return StringUtils.EMPTY;
        }
        return new BigDecimal(num).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 将毫克单位的重量转换为合适的单位（g, kg, t）
     *
     * @param milligrams 重量，单位为毫克
     * @return 转换后的重量字符串，包含数值和单位
     */
    public static String convertWeight(long milligrams) {
        if (milligrams < 1_000) {
            return milligrams + " mg";
        } else if (milligrams < 1_000_000) {
            double grams = milligrams / 1_000.0;
            return String.format("%.2f g", grams);
        } else if (milligrams < 1_000_000_000) {
            double kilograms = milligrams / 1_000_000.0;
            return String.format("%.2f kg", kilograms);
        } else {
            double tons = milligrams / 1_000_000_000.0;
            return String.format("%.2f t", tons);
        }
    }

    /**
     * 将立方毫米转换为立方米，保留两位小数
     *
     * @param cubicMilliMeter 立方毫米
     * @return
     */
    public static String convertCubicMeter(long cubicMilliMeter) {
        return BigDecimalUtils.genTransString(VolumeTransformUtil.transToCubicMeter(cubicMilliMeter, VolumeUnitEnum.CUBIC_MILLI_METER.getCode()));

    }


}
