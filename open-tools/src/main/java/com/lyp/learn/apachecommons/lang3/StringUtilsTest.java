package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 16:06
 * 方法很多，很实用，看源码即可
 */
public class StringUtilsTest {

    /**
     * truncate
     * 缩短字符串
     */
    @Test
    public void testTruncate(){
        System.out.println(StringUtils.truncate("12345678",2));
    }

    @Test
    public void testStrip(){
        System.out.println(StringUtils.strip(" abcdefabcdd","d"));
        System.out.println(StringUtils.strip(" abcdefabcdcdc","cd"));
        System.out.println(StringUtils.strip(" abcdefabcdcdc","cxyzd"));
    }
}
