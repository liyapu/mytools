package com.lyp.learn;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.List;
import java.util.StringTokenizer;

/**
 * @author: liyapu
 * @description:
 * @date 2020-08-16 22:31
 */
public class TestDemo {

    @Test
    public void buildGray() {
        String str = "139423\t青云谱区吉盛包装工厂店\n" +
                "48786\t西安浩诚聚鑫商贸有限公司\n" +
                "168443\t潮州市潮安区彩塘镇七和食品店";

        // Java 中我们可以使用 StringTokennizer 设置不同分隔符来分隔字符串，
        // 默认的分隔符是：空格、制表符（\t）、换行符(\n）、回车符（\r）。

        // 默认使用空格分隔
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreElements()) {
            final Object o = st.nextElement();
            Integer i = NumberUtils.toInt((String) o, 0);
            if (i > 0) {
                System.out.print(i + ",");
            }
        }
    }


    @Test
    public void test1() {
        List<String> strLists = Lists.newArrayList("aa", "bb", "cc", "dd", "ee");
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
