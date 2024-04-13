package com.lyp.learn.apachecommons.lang3;

import com.beust.jcommander.internal.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 19:49
 */
public class ArrayUtilsTest {

    @Test
    public void testSets() {
        Set<Integer> set1 = Sets.newHashSet();
        set1.add(1);
        set1.add(4);
        set1.add(5);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = Sets.newHashSet();
        set2.add(3);
        set2.add(1);
        set2.add(2);
        set2.add(4);


        System.out.println(set1.equals(set2));
    }

    @Test
    public void testConstantArray() {
        System.out.println(ArrayUtils.EMPTY_INT_ARRAY);
        System.out.println(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY);
    }

    @Test
    public void testCopy() {
        int[] intArr1 = new int[]{5, 8, 6, 3, 2, 9, 1};
        int[] clone = ArrayUtils.clone(intArr1);
        for (int i : clone) {
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
