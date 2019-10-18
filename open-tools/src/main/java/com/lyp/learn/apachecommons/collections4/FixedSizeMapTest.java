package com.lyp.learn.apachecommons.collections4;

import org.apache.commons.collections4.map.FixedSizeMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-18 16:17
 *
 * 固定长度的Map，阻止新增和删除
 * 任何改变长度的操作都不支持
 * 但是支持put操作改变已经存在Key的值
 */
public class FixedSizeMapTest {

    @Test
    public void test01() {
        FixedSizeMap<String, String> fixedSizeMap = FixedSizeMap.fixedSizeMap(new HashMap<String, String>() {{
            put("fang", "a");
            put("shi", "b");
            put("xiang", "c");
        }});

        System.out.println(fixedSizeMap);
        System.out.println(fixedSizeMap.isFull());
        System.out.println(fixedSizeMap.size());

        //不能再往里面添加数据了
        //java.lang.IllegalArgumentException: Cannot put new key/value pair - Map is fixed size
        //m.put("aaa", "aaa");

        //在我没有改变长度的情况下 是可以修改的
        fixedSizeMap.put("fang", "aaaaaaaa");
        System.out.println(fixedSizeMap);
    }
}
