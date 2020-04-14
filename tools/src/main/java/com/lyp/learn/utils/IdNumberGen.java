package com.lyp.learn.utils;

import cn.binarywang.tools.generator.ChineseIDCardNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-03 15:12
 */
public class IdNumberGen {


    @Test
    public void testGenerate() {
        String idCard = ChineseIDCardNumberGenerator.getInstance().generate();
        System.out.println("idCard = " + idCard);
        System.out.println(IDNumberUtils.isValid(idCard));
    }

    @Test
    public void testGenerateIssueOrg() {
        String issueOrg = ChineseIDCardNumberGenerator.generateIssueOrg();
        System.out.println("idCard = " + issueOrg);

    }

    @Test
    public void testGenerateValidPeriod() {
        String result = ChineseIDCardNumberGenerator.generateValidPeriod();
        System.out.println("result = " + result);

    }

}
