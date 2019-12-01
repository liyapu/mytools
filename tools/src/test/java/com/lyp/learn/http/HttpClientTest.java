package com.lyp.learn.http;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-10 18:16
 */
public class HttpClientTest {
    @Test
    public void testDoGet(){
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=203.0.42.1";
        String response = HttpClient.doGet(httpUrl);
        System.out.println(response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        System.out.println(jsonObject);
    }

    @Test
    public void testDoPost() {
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php";
        String params = "ip=203.0.42.1";
        String response = HttpClient.doPost(httpUrl,params);
        System.out.println(response);
    }
}
