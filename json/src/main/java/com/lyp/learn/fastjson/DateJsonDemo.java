package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

public class DateJsonDemo {
    public static void main(String[] args) {
        Model model = new Model();
        model.setId(100);
        model.setName("测试");
        model.setDate(new Date());


        //默认序列化
        String jsonString = JSON.toJSONString(model);
        System.out.println("默认序列化: " + jsonString);

        // 序列化处理时间，方式一
        jsonString = JSON.toJSONStringWithDateFormat(model, "yyyy-MM-dd HH:mm:ss");
        System.out.println("序列化处理时间，方式一: " + jsonString);

        // 序列化处理时间，方式二
        jsonString = JSON.toJSONStringWithDateFormat(model, "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("序列化处理时间，方式二: " + jsonString);



        // 序列化处理时间，方式三：ISO-8601日期格式
        jsonString = JSON.toJSONString(model, SerializerFeature.UseISO8601DateFormat);
        System.out.println("序列化处理时间，方式三：ISO-8601日期格式: " + jsonString);

        // 序列化处理时间，方式四：全局修改日期格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        jsonString = JSON.toJSONString(model, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("序列化处理时间，方式四：全局修改日期格式: " + jsonString);

        jsonString = JSON.toJSONString(model);
        System.out.println("默认序列化: " + jsonString);

    }
}
