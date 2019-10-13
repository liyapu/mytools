package com.lyp.learn.guava.math;

import com.google.common.math.IntMath;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IntMath 提供整型的实用方法。
 */
public class IntMathTest {

    /**
     * checkedAdd(int a, int b) 相加检测，整数a和整数b的和，如果和不超过整数范围，
     *                          就返回这个值.这个提供了一个额外的检查，如果结果溢出，则抛出ArithmeticException
     * checkedMultiply(int a, int b) 相乘检测，返回整数a和整数b的乘积，如果不超过整数返回，就返回这个值
     * divide(int p, int q, RoundingMode mode) 除法，返回将p除以q的结果，使用指定的RoundingMode进行舍入
     * checkedPow(int b, int k) 次幂检测，返回以b为底，以k为指数的幂，如果不超过整数范围，就返回这个值
     */
    @Test
    public void testCheckedAdd() {
        int result = IntMath.checkedAdd(1, 2);
        assertEquals(3, result);

//        IntMath.checkedAdd(Integer.MAX_VALUE, 100);
    }

    /**
     * checkedSubtract(int a, int b) 相减检测，整数a和整数b的差，如果不超过整数范围，就返回这个值
     */
    @Test
    public void testCheckedSubtract(){
        System.out.println(IntMath.checkedSubtract(10,2));
//        System.out.println(IntMath.checkedSubtract(Integer.MIN_VALUE,2));
    }

    /**
     * divide 返回p除以q的值，按照mode模式取整
     */
    @Test
    public void test_divide() {
        int result = IntMath.divide(10, 3, RoundingMode.CEILING);
        assertEquals(4, result);

        // 不需要舍入，如果用此模式进行舍入，应直接抛出ArithmeticException
//        int divide = IntMath.divide(10, 3, RoundingMode.UNNECESSARY);
    }

    /**
     * saturatedAdd 加法
     * 返回a和b的总和，除非它会溢出或下溢，在这种情况下分别返回Integer.MAX_VALUE或Integer.MIN_VALUE.
     *
     * saturatedMultiply(int a, int b)
     * 返回a和b的乘积，除非它会溢出或下溢，在这种情况下分别返回Integer.MAX_VALUE或Integer.MIN_VALUE.
     *
     * saturatedPow(int b, int k)
     * 将b返回到第k个幂，除非它会溢出或下溢，在这种情况下分别返回Integer.MAX_VALUE或Integer.MIN_VALUE.
     *
     * saturatedSubtract(int a, int b)
     * 返回a和b的差值，除非它会溢出或下溢，在这种情况下分别返回Integer.MAX_VALUE或Integer.MIN_VALUE.
     */
    @Test
    public void test_saturatedAdd() {
        int result = IntMath.saturatedAdd(6, 4);
        assertEquals(10, result);

        result = IntMath.saturatedAdd(Integer.MAX_VALUE, 1000);
        assertEquals(Integer.MAX_VALUE, result);
    }


    /**
     * binomial 计算n和k的二项式系数。它确保结果在整数范围内。否则，它给出Integer.MAX_VALUE
     */
    @Test
    public void test_binomial() {
        int result = IntMath.binomial(6, 3);
        System.out.println(result);

        result = IntMath.binomial(Integer.MAX_VALUE, 3);
        System.out.println(result);
    }

    /**
     * ceilingPowerOfTwo 计算2的最小幂的值，其大于或等于x。结果n是2^(n-1) < x < 2^n
     * 返回大于或等于x的最小2的幂
     */
    @Test
    public void test_ceilingPowerOfTwo() {
        int result = IntMath.ceilingPowerOfTwo(20);
        assertEquals(32, result);

        System.out.println(IntMath.ceilingPowerOfTwo(1));
        System.out.println(IntMath.ceilingPowerOfTwo(2));
        System.out.println(IntMath.ceilingPowerOfTwo(3));
        System.out.println(IntMath.ceilingPowerOfTwo(4));
        System.out.println(IntMath.ceilingPowerOfTwo(5));
        System.out.println(IntMath.ceilingPowerOfTwo(6));
    }




    /**
     * 。
     * factorial 计算前n个正整数乘积的因子值。如果n = 0，则返回1，如果结果不适合int范围，则返回Integer.MAX_VALUE
     * 阶乘。n!，如果n==0，则返回1; 如果结果不适合int，则返回Integer.MAX_VALUE
     */
    @Test
    public void test_factorial() {
        int result = IntMath.factorial(5);
        assertEquals(120, result);

        result = IntMath.factorial(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, result);
    }

    /**
     * floorPowerOfTwo 返回2的最大幂，其结果小于或等于x。结果n是 2^n < x < 2^(n+1)
     * 返回小于或等于x的最大2的幂
     */
    @Test
    public void test_floorPowerOfTwo() {
        int result = IntMath.floorPowerOfTwo(30);
        assertEquals(16, result);
    }

    /**
     * gcd a和b的最大公约数
     * 返回两个整数a和b的最大公因数，如果a == b == 0，那么返回0
     */
    @Test
    public void test_gcd() {
        int result = IntMath.gcd(30, 40);
        assertEquals(10, result);

        System.out.println(IntMath.gcd(3,5));
        System.out.println(IntMath.gcd(16,8));

    }

    /**
     * isPowerOfTwo 检查一个整数是不是可以换算成2的指数
     * 是否2的幂
     */
    @Test
    public void test_isPowerOfTwo() {
        boolean result = IntMath.isPowerOfTwo(16);
        assertTrue(result);

        result = IntMath.isPowerOfTwo(20);
        assertFalse(result);
    }

    /**
     * isPrime 质数
     */
    @Test
    public void test_isPrime() {
        boolean result = IntMath.isPrime(3);
        assertTrue(result);

        result = IntMath.isPrime(20);
        assertFalse(result);
    }

    /**
     * log10 返回10为底的对数。使用提供的舍入模式舍入结果
     * 10为底的对数，根据指定的舍入模式进行舍入.
     */
    @Test
    public void test_log10() {
        int result = IntMath.log10(30, RoundingMode.CEILING);
        assertEquals(2, result);
    }

    @Test
    public void test_log10_2() {
        IntMath.log10(30, RoundingMode.UNNECESSARY);
    }

    /**
     * log2 返回一个整数基于2的对数
     */
    @Test
    public void test_log2() {
        int result = IntMath.log2(30, RoundingMode.CEILING);
        assertEquals(5, result);

        System.out.println(IntMath.log2(8,RoundingMode.CEILING));
    }

    @Test
    public void test_log2_2() {
        IntMath.log2(30, RoundingMode.UNNECESSARY);
    }

    /**
     * mean 计算两个值的平均值
     */
    @Test
    public void test_mean() {
        int result = IntMath.mean(30, 20);
        assertEquals(25, result);
    }

    /**
     * mod 返回另一个数字的整数除法的余数
     */
    @Test
    public void test_mod() {
        int result = IntMath.mod(30, 4);
        assertEquals(2, result);
    }

    /**
     * pow 返回b的值为k的幂
     */
    @Test
    public void test_pow() {
        int result = IntMath.pow(6, 4);
        assertEquals(1296, result);
    }



    /**
     * sqrt 返回给定数字的平方根。使用提供的舍入模式舍入结果
     */
    public void test_sqrt() {
        int result = IntMath.sqrt(30, RoundingMode.CEILING);
        assertEquals(6, result);
    }

    /**
     * sqrt(int x, RoundingMode mode)
     * 平方根，整数的开平方根，根据mode模式取整.
     */
    @Test
    public void test_sqrt2() {
        System.out.println(IntMath.sqrt(16, RoundingMode.UNNECESSARY));
        System.out.println(IntMath.sqrt(100, RoundingMode.UNNECESSARY));

        //java.lang.ArithmeticException: mode was UNNECESSARY, but rounding was necessary
//        System.out.println(IntMath.sqrt(30, RoundingMode.UNNECESSARY));
        System.out.println(IntMath.sqrt(30, RoundingMode.CEILING));
        System.out.println(IntMath.sqrt(30, RoundingMode.FLOOR));
        System.out.println(IntMath.sqrt(30, RoundingMode.DOWN));
    }

}

