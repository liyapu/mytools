package com.lyp.learn.hutool.core.util;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-21 11:27
 */
public class StrUtilTest {

    @Test
    public void test11(){
        boolean blank = StrUtil.isAllBlank("aa","bb");
        System.out.println("blank = " + blank);
    }
}
