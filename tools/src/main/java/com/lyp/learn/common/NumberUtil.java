package com.lyp.learn.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;

/**
 * 数字工具类
 *
 * @author at 2023/11/24 14:23
 */
@SuppressWarnings("unused")
public class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 常用常量
     */

    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = (short) 0;
    public static final Short SHORT_ONE = (short) 1;
    public static final Short SHORT_MINUS_ONE = (short) -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = 0.0D;
    public static final Double DOUBLE_ONE = 1.0D;
    public static final Double DOUBLE_MINUS_ONE = -1.0D;
    public static final Float FLOAT_ZERO = 0.0F;
    public static final Float FLOAT_ONE = 1.0F;
    public static final Float FLOAT_MINUS_ONE = -1.0F;

    /**
     * 保留固定小数位数，舍去多余位数
     *
     * @param value 需要科学计算的数据
     * @param scale 保留的小数位
     * @return 结果
     * @since 1.0.0
     */
    public static BigDecimal roundDown(BigDecimal value, int scale) {
        return round(value, scale, RoundingMode.DOWN);
    }

    /**
     * 保留固定小数位数，舍去多余位数
     *
     * @param number 需要科学计算的数据
     * @param scale  保留的小数位
     * @return 结果
     * @since 1.0.0
     */
    public static BigDecimal roundDown(Number number, int scale) {
        return roundDown(toBigDecimal(number), scale);
    }

    /**
     * 数字转{@link BigDecimal}<br>
     * Float、Double等有精度问题，转换为字符串后再转换<br>
     * null默认为null
     *
     * @param number 数字
     * @return {@link BigDecimal}
     * @since 1.0.0
     */
    public static BigDecimal toBigDecimal(Number number) {
        if (null == number) {
            return null;
        }

        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof Long) {
            return new BigDecimal((Long) number);
        } else if (number instanceof Integer) {
            return new BigDecimal((Integer) number);
        } else if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        }

        // Float、Double等有精度问题，转换为字符串后再转换
        return toBigDecimal(number.toString());
    }

    /**
     * 数字转{@link BigDecimal}<br>
     * null或""或空白符转换为0
     *
     * @param number 数字字符串
     * @return {@link BigDecimal}
     * @since 1.0.0
     */
    public static BigDecimal toBigDecimal(String number) {
        try {
            number = parseNumber(number).toString();
        } catch (Exception ignore) {
            // 忽略解析错误
        }
        return StrUtil.isBlank(number) ? BigDecimal.ZERO : new BigDecimal(number);
    }

    /**
     * 将指定字符串转换为{@link Number} 对象
     *
     * @param numberStr Number字符串
     * @return Number对象
     * @throws NumberFormatException 包装了{@link ParseException}，当给定的数字字符串无法解析时抛出
     * @since 1.0.0
     */
    public static Number parseNumber(String numberStr) throws NumberFormatException {
        try {
            return NumberFormat.getInstance().parse(numberStr);
        } catch (ParseException e) {
            final NumberFormatException nfe = new NumberFormatException(e.getMessage());
            nfe.initCause(e);
            throw nfe;
        }
    }

    /**
     * 保留固定位数小数<br>
     * 例如保留四位小数：123.456789 =》 123.4567
     *
     * @param number       数字值
     * @param scale        保留小数位数，如果传入小于0，则默认0
     * @param roundingMode 保留小数的模式 {@link RoundingMode}，如果传入null则默认四舍五入
     * @return 新值
     * @since 1.0.0
     */
    public static BigDecimal round(BigDecimal number, int scale, RoundingMode roundingMode) {
        if (null == number) {
            return null;
        }
        if (scale < 0) {
            scale = 0;
        }
        if (null == roundingMode) {
            roundingMode = RoundingMode.HALF_UP;
        }

        return number.setScale(scale, roundingMode);
    }

    /**
     * 格式化double<br>
     * 对 {@link DecimalFormat} 做封装<br>
     *
     * @param pattern 格式 格式中主要以 # 和 0 两种占位符号来指定数字长度。0 表示如果位数不足则以 0 填充，# 表示只要有可能就把数字拉上这个位置。<br>
     *                <ul>
     *                <li>0 =》 取一位整数</li>
     *                <li>0.00 =》 取一位整数和两位小数</li>
     *                <li>00.000 =》 取两位整数和三位小数</li>
     *                <li># =》 取所有整数部分</li>
     *                <li>#.##% =》 以百分比方式计数，并取两位小数</li>
     *                <li>#.#####E0 =》 显示为科学计数法，并取五位小数</li>
     *                <li>,### =》 每三位以逗号进行分隔，例如：299,792,458</li>
     *                <li>光速大小为每秒,###米 =》 将格式嵌入文本</li>
     *                </ul>
     * @param value   值，支持BigDecimal、BigInteger、Number等类型
     * @return 格式化后的值
     * @since 1.0.0
     */
    public static String decimalFormat(String pattern, Object value) {
        return decimalFormat(pattern, value, null);
    }

    /**
     * 格式化double<br>
     * 对 {@link DecimalFormat} 做封装<br>
     *
     * @param pattern      格式 格式中主要以 # 和 0 两种占位符号来指定数字长度。0 表示如果位数不足则以 0 填充，# 表示只要有可能就把数字拉上这个位置。<br>
     *                     <ul>
     *                     <li>0 =》 取一位整数</li>
     *                     <li>0.00 =》 取一位整数和两位小数</li>
     *                     <li>00.000 =》 取两位整数和三位小数</li>
     *                     <li># =》 取所有整数部分</li>
     *                     <li>#.##% =》 以百分比方式计数，并取两位小数</li>
     *                     <li>#.#####E0 =》 显示为科学计数法，并取五位小数</li>
     *                     <li>,### =》 每三位以逗号进行分隔，例如：299,792,458</li>
     *                     <li>光速大小为每秒,###米 =》 将格式嵌入文本</li>
     *                     </ul>
     * @param value        值，支持BigDecimal、BigInteger、Number等类型
     * @param roundingMode 保留小数的方式枚举
     * @return 格式化后的值
     * @since 1.0.0
     */
    public static String decimalFormat(String pattern, Object value, RoundingMode roundingMode) {
        if (value instanceof Number) {
            Assert.isTrue(isValidNumber((Number) value), "value is NaN or Infinite!");
        }
        final DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if (null != roundingMode) {
            decimalFormat.setRoundingMode(roundingMode);
        }
        return decimalFormat.format(value);
    }

    /**
     * 检查是否为有效的数字<br>
     * 检查Double和Float是否为无限大，或者Not a Number<br>
     * 非数字类型和Null将返回true
     *
     * @param number 被检查类型
     * @return 检查结果，非数字类型和Null将返回true
     * @since 1.0.0
     */
    public static boolean isValidNumber(Number number) {
        if (number instanceof Double) {
            return (!((Double) number).isInfinite()) && (!((Double) number).isNaN());
        } else if (number instanceof Float) {
            return (!((Float) number).isInfinite()) && (!((Float) number).isNaN());
        }
        return true;
    }

    /**
     * 检查是否为 {@code 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，{@code 0} 将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean isZero(Integer integer) {
        return Objects.equals(integer, 0);
    }

    /**
     * 检查是否为 {@code 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，{@code 0} 将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean isZero(Long integer) {
        return Objects.equals(integer, 0L);
    }

    /**
     * 检查是否为负整数；{@code integer < 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，负整数将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean isNegative(Integer integer) {
        return Objects.nonNull(integer) && integer < 0;
    }

    /**
     * 检查是否为负整数；{@code integer < 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，负整数将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean isNegative(Long integer) {
        return Objects.nonNull(integer) && integer < 0L;
    }

    /**
     * 检查是否为正整数；{@code integer >= 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，正整数将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean isNatural(Integer integer) {
        return Objects.nonNull(integer) && integer > 0;
    }

    /**
     * 检查是否为正整数；{@code integer >= 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，正整数将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean isNatural(Long integer) {
        return Objects.nonNull(integer) && integer > 0L;
    }

    /**
     * 检查是否为非自然数；{@code integer <= 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，非自然数将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean notNatural(Integer integer) {
        return Objects.nonNull(integer) && integer <= 0;
    }

    /**
     * 检查是否为非自然数；{@code integer <= 0}
     *
     * @param integer 被检查整数
     * @return 检查结果，非自然数将返回true
     * @author kangyonggen
     * @since 1.2.1
     */
    public static boolean notNatural(Long integer) {
        return Objects.nonNull(integer) && integer <= 0L;
    }
}
