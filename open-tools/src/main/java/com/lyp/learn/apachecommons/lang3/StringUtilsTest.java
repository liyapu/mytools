package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 16:06
 * 方法很多，很实用，看源码即可
 */
public class StringUtilsTest {

    @Test
    public void test1111() {
        int a = 9999;
        Integer b = 9999;
        boolean flag = Objects.equals(b, a);
        System.out.println(flag);
    }

    /**
     * truncate
     * 缩短字符串
     */
    @Test
    public void testTruncate() {
        System.out.println(StringUtils.truncate("12345678", 2));
    }

    @Test
    public void testStrip() {
        System.out.println(StringUtils.strip(" abcdefabcdd", "d"));
        System.out.println(StringUtils.strip(" abcdefabcdcdc", "cd"));
        System.out.println(StringUtils.strip(" abcdefabcdcdc", "cxyzd"));
    }

    @Test
    public void testDel() {
        String str = "{\n" +
                "  \"uniqueKey\": \"W66_lyp_0002\",\n" +
                "  \"billType\": 1000,\n" +
                "  \"operator\": \"liyiputestmock\",\n" +
                "  \"optTime\": \"1712107902740\",\n" +
                "  \"status\": 20,\n" +
                "  \"details\": [\n" +
                "    {\n" +
                "      \"supplierId\": \"6031\",\n" +
                "      \"skuId\": \"100301266053305\",\n" +
                "      \"unitId\": \"206\",\n" +
                "      \"lotId\": \"53607549\",\n" +
                "      \"actualReceiptQuantity\": \"15\",\n" +
                "      \"diffReceiptQuantity\": \"0\",\n" +
                "      \"produceDate\": \"1702828800000\",\n" +
                "      \"instorageDate\": \"1703174400000\",\n" +
                "      \"targetBinDetails\": [\n" +
                "        {\n" +
                "          \"targetBinCode\": \"SH-01-01-01-01\",\n" +
                "          \"targetBinId\": \"11032555\",\n" +
                "          \"inLotId\": \"53613537\",\n" +
                "          \"actualReceiptQuantity\": \"15\",\n" +
                "          \"putawayFlag\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"diffDetails\": []\n" +
                "}";

        final String result = StringUtils.deleteWhitespace(str);
        System.out.println("result ===== " + result);
    }
}
