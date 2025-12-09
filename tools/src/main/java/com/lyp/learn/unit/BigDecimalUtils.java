package com.lyp.learn.unit;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author liyapu
 * @date 2024-10-16 11:07
 * @description BigDecimal工具类
 */
public class BigDecimalUtils {

    //private static final BigDecimal hundred = new BigDecimal(100);
    //private static final BigDecimal thousand = new BigDecimal(10000);

    /**
     * 转换String保留2位
     */
    public static String genTransString(BigDecimal num) {
        if (num == null) {
            num = BigDecimal.ZERO;
        } else {
            num = num.setScale(2, RoundingMode.HALF_UP);
        }
        return num.stripTrailingZeros().toPlainString();
    }

    ///**
    // * 转换为亿元保留2位,返回String
    // */
    //public static String genMillon(BigDecimal num) {
    //    if (num == null) {
    //        num = BigDecimal.ZERO;
    //    } else {
    //        num = num.divide(thousand, 2, BigDecimal.ROUND_HALF_UP);
    //    }
    //    return num.stripTrailingZeros().toPlainString();
    //}

    ///**
    // * 相除，转换为亿元保留2位,返回String
    // */
    //public static String genMillonTwo(BigDecimal num1, BigDecimal num2) {
    //    if (num1 == null) {
    //        num1 = BigDecimal.ZERO;
    //    }
    //    if (num2 == null) {
    //        num2 = BigDecimal.ZERO;
    //    }
    //    if (compare(num2)) {
    //        return BigDecimal.ZERO.toPlainString();
    //    } else {
    //        num1 = num1.divide(thousand, 4, BigDecimal.ROUND_HALF_UP);
    //        num2 = num2.divide(thousand, 4, BigDecimal.ROUND_HALF_UP);
    //        return num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
    //    }
    //}

    ///**
    // * String类型的万元转换为亿元
    // *
    // * @return
    // */
    //public static String genMillonString(String num) {
    //    BigDecimal bd;
    //    if (ObjectUtils.isNotEmpty(num)) {
    //        bd = new BigDecimal(num);
    //    } else {
    //        bd = BigDecimal.ZERO;
    //    }
    //    return bd.divide(thousand, 2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    //}

    /**
     * 相加之和，且四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genAddNum(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.add(num2).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 相减之差，且四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genSubtractNum(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        return num1.subtract(num2).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 相乘之积，且四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genMultiplyNum(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        if (compare(num2)) {
            return BigDecimal.ZERO;
        } else {
            return num1.multiply(num2).setScale(2, RoundingMode.HALF_UP);
        }
    }

    /**
     * int类型数相乘，且四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genIntMultiplyNum(Integer num1, Integer num2) {
        if (num1 == null) {
            num1 = 0;
        }
        if (num2 == null) {
            num2 = 0;
        }
        if (num2.intValue() == 0) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(num1).multiply(new BigDecimal(num2))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * 两数相除，且四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genDivideNum(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        if (compare(num2)) {
            return BigDecimal.ZERO;
        } else {
            return num1.divide(num2, 2, RoundingMode.HALF_UP);
        }
    }

    /**
     * 两数相除，四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genBigDecimalNum(BigDecimal num1, BigDecimal num2) {
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        if (compare(num2)) {
            return BigDecimal.ZERO;
        }
        return num1.divide(num2, 4, RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    /**
     * 两个int类型数相除，四舍五入，保留两位小数，最后转为String类型
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String genIntToStringNum(Integer num1, Integer num2) {
        if (num1 == null) {
            num1 = 0;
        }
        if (num2 == null) {
            num2 = 0;
        }
        if (num2.intValue() == 0) {
            return BigDecimal.ZERO.toPlainString();
        }
        return new BigDecimal(num1).divide(new BigDecimal(num2), 4, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();

    }

    /**
     * 两个int类型数相除，四舍五入，保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal genIntToBigDecimalNum(Integer num1, Integer num2) {
        if (num1 == null) {
            num1 = 0;
        }
        if (num2 == null) {
            num2 = 0;
        }
        if (num2.intValue() == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(num1).divide(new BigDecimal(num2), 4, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);

    }

    /**
     * 与0相比较，是否相等
     *
     * @param num
     * @return
     */
    public static Boolean compare(BigDecimal num) {
        if (num.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("123.4567");
        BigDecimal b2 = new BigDecimal("123.4567000");

        System.out.println(b1.stripTrailingZeros().toPlainString());
        System.out.println(b1.setScale(0, RoundingMode.HALF_UP).toPlainString());
        System.out.println(b1.setScale(0, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
        System.out.println(b1.setScale(6, RoundingMode.HALF_UP).toPlainString());
        System.out.println(b1.setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());

        System.out.println("----------------");
        System.out.println(b2.stripTrailingZeros().toPlainString());
        System.out.println(b2.setScale(0, RoundingMode.HALF_UP).toPlainString());
        System.out.println(b2.setScale(0, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
        System.out.println(b2.setScale(6, RoundingMode.HALF_UP).toPlainString());
        System.out.println(b2.setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
    }


}


