package com.lyp.learn.apachecommons.lang3.math;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 15:56
 */
public class NumberUtilsTest {

    /**
     * 常量
     */
    @Test
    public void testConstant(){
        System.out.println(NumberUtils.INTEGER_TWO);
        System.out.println(NumberUtils.INTEGER_ONE);
        System.out.println(NumberUtils.INTEGER_ZERO);
        System.out.println(NumberUtils.INTEGER_MINUS_ONE);
        System.out.println();

        System.out.println(NumberUtils.LONG_ONE);
        System.out.println(NumberUtils.LONG_ZERO);
        System.out.println(NumberUtils.LONG_MINUS_ONE);
    }

    @Test
    public void testCompare(){
        System.out.println(NumberUtils.compare(6,6));
        System.out.println(NumberUtils.compare(6,10));
        System.out.println(NumberUtils.compare(6,1));
    }

    @Test
    public void testMax(){
        System.out.println(NumberUtils.max(1,6,8,4,3,2,66,99,11,23));
        int[] arrInt = new int[] {4,6,7,89,3,2,5,6,88,99,22,44,66};
        System.out.println(NumberUtils.max(arrInt));
    }
}
