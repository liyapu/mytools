package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

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
}
