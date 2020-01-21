package com.lyp.learn.hutool.core.util;

import cn.hutool.core.util.IdcardUtil;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2020-01-21 14:11
 */
public class IdcardUtilTest {

    @Test
    public void testIdNum(){
        String idNum = "110101199003075939";
        boolean isValid = IdcardUtil.isValidCard(idNum);
        String birth = IdcardUtil.getBirthByIdCard(idNum);
        int age = IdcardUtil.getAgeByIdCard(idNum);
        System.out.println("isValid = " + isValid);
        System.out.println("birth = " + birth);
        System.out.println("age = " + age);

    }
}
