package com.lyp.learn.http;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-10 21:26
 */
public class HttpClient3Test {

    @Test
    public void testDoGet(){
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=203.0.42.1";
        String response = HttpClient3.doGet(httpUrl);
        System.out.println(response);
    }

    @Test
    public void testDoPost(){
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("ip","8.8.8.8");
        String response = HttpClient3.doPost(httpUrl,paramMap);
        System.out.println(response);
    }
}
