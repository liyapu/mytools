package com.lyp.learn.fastjson;

import com.alibaba.fastjson.JSONPath;

import java.util.List;

public class JSONPathExtractDemo {
    public static void main(String[] args) {
        String jsonStr = "{\"code\":0,\"messsage\":\"成功\",\"result\":{\"pushId\":366,\"isGril\":true," +
                "\"city\":\"北京\",\"url\":\"www.www.com\",\"name\":\"万维网\"," +
                "\"sites\":{\"site\":[{\"id\":1,\"name\":\"菜鸟教程\",\"url\":\"www.runoob.com\"}," +
                "{\"id\":2,\"name\":\"菜鸟工具\"},{\"id\":3,\"name\":\"Google\"}," +
                "{\"id\":4,\"name\":\"baidu\",\"url\":\"www.baidu.com\"}]}," +
                "\"books\":{\"book\":[{\"id\":1,\"name\":\"java编程思想\"}," +
                "{\"id\":100,\"name\":\"fastJosn教程\"}]}}}";

        //获取pushId
        int pushId = (int)JSONPath.read(jsonStr,"$.result.pushId");
        System.out.println(pushId);
        int pushId2 = (int)JSONPath.extract(jsonStr,"$.result.pushId");
        System.out.println(pushId2);
        //获取city
        String city = (String)JSONPath.read(jsonStr,"$.result.city");
        System.out.println(city);
        String city2 = (String)JSONPath.extract(jsonStr,"$.result.city");
        System.out.println(city2);
        System.out.println("------------");
        //获取result下sites下site的所有name
        List<String> nameList1 = (List<String>) JSONPath.read(jsonStr,"$.result.sites.site.name");
        System.out.println(nameList1);
        //获取所有的name
        List<String> nameList2 = (List<String>)JSONPath.read(jsonStr,"$..name");
        System.out.println(nameList2);
        //获取result下sites下site中的所有对象
        List<Object> objectList = (List<Object>)JSONPath.read(jsonStr,"$.result.sites.site[*]");
        System.out.println(objectList);
        //获取json中sites下site数组中包含 url 的所有值
        List<Object> urlList = (List<Object>)JSONPath.read(jsonStr,"$.result.sites.site[?(@.url)]");
        System.out.println(urlList);
        //获取json中sites下site数组中包含 id  <=3 的所有值
        List<Object> idList = (List<Object>)JSONPath.read(jsonStr,"$.result.sites.site[?(@.id <= 3)]");
        System.out.println(idList);

        //获取json中sites下site数组中 name=baidu的
        List<Object> nameList3 = (List<Object>) JSONPath.read(jsonStr,"$.result.sites.site[?(@.name='baidu')]");
        System.out.println(nameList3);
        //获取json中sites下的所有id
        List<Integer> idList2 = (List<Integer>)JSONPath.read(jsonStr,"$.result.sites..id");
        System.out.println(idList2);
        //获取json中sites下site中的前2值
        List<Object> objectList2 = (List<Object>) JSONPath.read(jsonStr,"$.result.sites.site[:1]");
        System.out.println(objectList2);
        //获取json中sites下site数组次长度
        int siteLength = (int)JSONPath.read(jsonStr,"$.result.sites.site.size()");
        System.out.println(siteLength);


    }
}
