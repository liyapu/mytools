package com.lyp.learn.http;

import com.alibaba.fastjson.JSONObject;
import com.lyp.learn.pojo.JsonRootBean;
import com.lyp.learn.pojo.TaskList;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-10 21:43
 */
public class HttpClient4Test {
    @Test
    public void testDoGet(){
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=8.8.8.8";
        //String httpUrl = "http://test-browser.coohua.top/browser/config/appStart?os=ios&userId=222223&appVersion=1.3.5";
        String response = HttpClient4.doGet(httpUrl);
        System.out.println(response);
    }

    /**
     * 杨大哥 头条文章
     */
    @Test
    public void tstDoGet3() throws InterruptedException {
//        for(int i = 1 ; i <= 100; i++){
//
//            testDoGet2(i);
//            Random random = new Random();
//            int num = random.nextInt(10);
//            System.out.println(System.currentTimeMillis());
//            System.out.println("nnnnnnnnnnnnnnnnnnn " + num);
//            Thread.sleep(num * 1000);
//            System.out.println(System.currentTimeMillis());

//        }
    }
    public void testDoGet2(int i ){
        System.out.println("======================== i = " + i);
        String url = "https://www.toutiao.com/a1651715977010179";

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            //httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 设置请求头
//            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            httpGet.setHeader("Accept-Encoding", "gzip,deflate");
            httpGet.setHeader("Referer","https://www.toutiao.com/c/user/102226312698/");
            //设置请求的报文头部的编码
            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            //设置期望服务端返回的编码
            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            httpGet.setHeader("Cookie","tt_webid=6765441546869179918; WEATHER_CITY=%E5%8C%97%E4%BA%AC; s_v_web_id=dc1d505e1af5dec3fd18d48f9bb6ca3b; __tasessionId=tv9k6xtmk1575202128547; tt_webid=6765441546869179918; csrftoken=51f0839cc06491b76ab6faa14d7fafb1; UM_distinctid=16ec1821535145-06b42fdaeff49a-376b4502-144000-16ec18215368a3; CNZZDATA1259612802=1545522741-1575204192-https%253A%252F%252Fwww.toutiao.com%252F%7C1575204192; _ga=GA1.2.1427115044.1575204558; _gid=GA1.2.573067360.1575204558");

            //log.info("HttpClient doGet request.url:{}",url);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity,"UTF-8");
            System.out.println("result ::::::" + result);

//            log.info("HttpClient doGet response.url:{},result:{}",url,result);
        } catch (ClientProtocolException e) {
            //log.error("HttpClient doGet err.",e);
            e.printStackTrace();
        } catch (IOException e) {
            //log.error("HttpClient doGet err.",e);
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testDoPost(){
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php";
        String url = "http://test-browser.coohua.top/browser/config/appStart";

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("os","ios");
        paramMap.put("appVersion","1.3.5");
        paramMap.put("userId","222223");
        String response = HttpClient4.doPost(url,paramMap);
        System.out.println(response);
    }

    @Test
    public void testDoPost2(){
        String httpUrl = "http://ip.taobao.com/service/getIpInfo.php";

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("ip","120.34.56.66");

        String response = HttpClient4.doPost(httpUrl,paramMap);
        System.out.println(response);
    }

    @Test
    public void testDoPost3(){
        String httpUrl = "http://browser.coohua.com/browser/user/center/taskList";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("debugCode","agvfsdji43j09dfjkshf74wfsjkfe9s");
        paramMap.put("userId",3143921);

        for(int i = 1; i <= 200; i++){
            String response = HttpClient4.doPost(httpUrl,paramMap);
            JsonRootBean jsonRootBean = JSONObject.parseObject(response,JsonRootBean.class);
            List<TaskList> taskList = jsonRootBean.getResult().getTaskList();
            for(TaskList tl : taskList){
                if(tl.getTaskId() == 6){
                    System.out.println("taskId=" + tl.getTaskId());
                    System.out.println(response);
                }
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
