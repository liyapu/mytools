package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.Date;

public class FieldEntityDemo {
    public static void main(String[] args) {
        FieldEntity fe = new FieldEntity();
        fe.setId(100);
        fe.setMama("母亲");
        fe.setName("张三");
        fe.setNickName("小三");
        fe.setToday(new Date());
        fe.setBirthday(new Date());
        fe.setMoney(88888888);
        fe.setPhone("136111122222");
        String jsonString = JSON.toJSONString(fe);
        System.out.println(jsonString);
        //{"UUID":100,"mother":"母亲","name":"张三","today":"2018-11-28 23:28:59","birthday":"20181128",
        // "money":"88888888元工资入账","phone":"136111122222"}

        String jsonStr = "{\"UUID\":100,\"mother\":\"母亲\",\"name\":\"张三\",\"today\":\"2018-11-28 23:28:59\"," +
                "\"birthday\":\"20181128\",\"money\":\"88888888\",\"phone\":\"136111122222\"}\n";

        FieldEntity fe2 = JSON.parseObject(jsonStr, FieldEntity.class);
        System.out.println(fe2);
        //{id=100, mama='母亲', name='张三', nickName='null', today=Wed Nov 28 23:28:59 CST 2018,
        // birthday=Wed Nov 28 00:00:00 CST 2018, money=88888888, phone='null'}
    }
}
