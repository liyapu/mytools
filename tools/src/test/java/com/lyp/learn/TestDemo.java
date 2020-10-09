package com.lyp.learn;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2020-08-16 22:31
 */
public class TestDemo {

    @Test
    public void test1(){
        List<String> strLists = Lists.newArrayList("aa","bb","cc","dd","ee");
        List<String> stringList = strLists.subList(0, 2);
//        System.out.println(JSON.toJSONString(stringList));
        System.out.println(stringList);

    }

    public static void main(String[] args) {
        int sort = 15;
        String str = "INSERT INTO `wodaotu2`.`official_cate_web_rel` (`cate_id`, `web_id`, `sort`, `status`, `create_time`, `update_time`) VALUES ('9', '%d', '%d', '1', '2020-08-15 12:25:45', '2020-08-15 12:25:45');";
        for (int i = 31; i <=84 ; i++) {
            String s = String.format(str, i, sort);
            sort += 5;
            System.out.println(s);
        }
    }
}
