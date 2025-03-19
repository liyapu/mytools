package com.lyp.learn.jackson;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author liyapu
 * @date 2025-03-19 20:03
 * @description
 */
public class JsonDiffUtilsTest {

    @Test
    public void testDiffJson01() {
        //diff结果 1:一致;  2:不一致; 0:解析失败;
        String json1 = "{\"a\": {\"b\": [3,2,1]}, \"x\": 5}";
        String json2 = "{\"a\": {\"b\": [1,2,3]}, \"x\": 5}";

        //json次序不同，不忽略，次序敏感 ----> 2:不一致
        int result0 = JsonDiffUtils.diffJson(json1, json2, Arrays.asList(), true);
        System.out.println("result0 === " + result0);

        //json次序不同，不忽略，次序不敏感 ----> 1:一致
        int result1 = JsonDiffUtils.diffJson(json1, json2, Arrays.asList(), false);
        System.out.println("result1 === " + result1);

        //json次序不同，忽略，次序敏感 ----> 1:一致
        int result2 = JsonDiffUtils.diffJson(json1, json2, Arrays.asList("a.b"), false);
        System.out.println("result2 === " + result2);


        // 忽略路径 "a.b"，数组顺序不敏感
        int result3 = JsonDiffUtils.diffJson(json1, json2, Arrays.asList("a.b"), false);
        System.out.println("result3 === " + result3);

        // 不忽略路径，数组顺序敏感
        int result4 = JsonDiffUtils.diffJson(json1, json2, Collections.emptyList(), true);
        System.out.println("result4 === " + result4);
    }


}
