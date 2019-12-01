package com.lyp.learn.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;


/**
 * @Author: liyapu
 * @Description:
 * 目前JAVA实现HTTP请求的方法用的最多的有两种：
 * 第一种：：：：：：：：：：：
 * 是通过HTTPClient这种第三方的开源框架去实现。HTTPClient对HTTP的封装性比较不错，通过它基本上能够满足我们大部分的需求，
 *        HttpClient3.1 是 org.apache.commons.httpclient下操作远程 url的工具包，虽然已不再更新，但实现工作中使用httpClient3.1的代码还是很多，
 *        HttpClient4.5是org.apache.http.client下操作远程 url的工具包，最新的；
 * 第二种：：：：：：：：：：：
 *        是通过HttpURLConnection去实现，HttpURLConnection是JAVA的标准类，是JAVA比较原生的一种实现方式
 * @create: 2018-10-10 17:52
 *    HttpClient4.5 实现方式 示例：
 *
 *    有时候我们在使用post请求时，可能传入的参数是json或者其他格式，此时我们则需要更改请求头及参数的设置信息，以httpClient4.5为例，更改下面两列配置：
 *    httpPost.setEntity(new StringEntity("你的json串"));
 *    httpPost.addHeader("Content-Type", "application/json")。
 */


public class HttpClient4 {

    public static String doGet(String url) {
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
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            httpGet.setHeader("Accept-Encoding", "gzip,deflate");
            //设置请求的报文头部的编码
            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            //设置期望服务端返回的编码
            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            //log.info("HttpClient doGet request.url:{}",url);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity,"UTF-8");
            //log.info("HttpClient doGet response.url:{},result:{}",url,result);
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
        return result;
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        httpPost.setHeader("Accept-Encoding", "gzip,deflate");
        //设置请求的报文头部的编码
        httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        //设置期望服务端返回的编码
        httpPost.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));

        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                //log.error("HttpClient doPost err.",e);
                e.printStackTrace();
            }
        }
        //log.info("HttpClient doPost request.url:{},params:{}",url,paramMap);
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity,"UTF-8");
            //log.info("HttpClient doPost response.url:{},,result:{}",url,paramMap,result);
        } catch (ClientProtocolException e) {
            //log.error("HttpClient doPost err.",e);
            e.printStackTrace();
        } catch (IOException e) {
            //log.error("HttpClient doPost err.",e);
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
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
        return result;
    }



}