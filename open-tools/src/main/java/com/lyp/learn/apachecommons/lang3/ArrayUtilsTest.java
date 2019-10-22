package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 19:49
 */
public class ArrayUtilsTest {

    @Test
    public void testConstantArray(){
        System.out.println(ArrayUtils.EMPTY_INT_ARRAY);
        System.out.println(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY);
    }

    @Test
    public void testCopy(){
        int[] intArr1 = new int[]{5,8,6,3,2,9,1};
        int[] clone = ArrayUtils.clone(intArr1);
        for(int i : clone){
            System.out.println(i);
        }
    }

    /**
     * 翻转数字
     */
    @Test
    public void testReverse(){
        int[] intArr1 = new int[]{5,8,6,3,2,9,1};
        System.out.println(Arrays.toString(intArr1));

        ArrayUtils.reverse(intArr1);

        System.out.println(Arrays.toString(intArr1));
    }
}
