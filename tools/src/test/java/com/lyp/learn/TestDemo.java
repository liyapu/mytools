package com.lyp.learn;

/**
 *@author: liyapu
 *@description:
 *@date 2020-08-16 22:31
 */
public class TestDemo {
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
