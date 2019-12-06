package com.lyp.learn.base.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 使用淘新闻 提供的ip区域判断方法
 *  wiki地址： http://confluence.coohua.com/display/Developer/ip2area
 * @create: 2018-10-09 09:39
 */
public class IpAreaUtils {

    private static List<IpBlock> ipBlockList = null;

    // 二分查找法查到 IP 是否属于中国
    public static Boolean isInChina(String ip){
        if(ListUtils.isEmpty(ipBlockList)){
            System.out.println("ipBlockList is empty!!!!!!!!!");
            return true;
        }
        int start = 0, end = ipBlockList.size()-1;
        int index = 0;
        long ipLong = getLong(ip);

        while(true){
            index = (start + end) / 2;
            IpBlock current = ipBlockList.get(index);

            if(current.start <= ipLong && current.end >= ipLong){
                return true;
            }

            if(end - start <= 1){
                IpBlock startBlock = ipBlockList.get(start);
                IpBlock endBlock = ipBlockList.get(end);
                if(!(startBlock.start <= ipLong && startBlock.end >= ipLong) && !(endBlock.start <= ipLong && endBlock.end >= ipLong)){
                    return false;
                }
            }

            if(current.start > ipLong){
                end = index;
                continue;
            }

            if(current.end < ipLong){
                start = index;
                continue;
            }

        }
    }

    static {
        // 读取文件
        try {
            // 后续改造，需要固定 CDN 链接
            String ipRawContent = getText("https://imgs-xwz.coohua.com/20181009/201810090925175060.txt");
            String[] lines = ipRawContent.split("\n");

            // 初始化 IpBlockList
            ipBlockList = new ArrayList<>(lines.length);

            for (String line : lines) {
                String[] items = line.split("\\t");

                ipBlockList.add(new IpBlock(
                        items[0],
                        getLong(items[0]),
                        getLong(items[1])
                ));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static Long getLong(String ip) {
        String[] items = ip.trim().split("\\.");
        Long result = 0L;
        try {
            result +=  (Long.parseLong(items[0]) << 24 & 0xFF000000);
            result +=  (Long.parseLong(items[1]) << 16 & 0xFF0000);
            result +=  (Long.parseLong(items[2]) << 8 & 0xFF00);
            result +=  (Long.parseLong(items[3]) & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * IpBlock 封装 ip 段
     */
    static class IpBlock{
        String ip;
        long start;
        long end;

        public IpBlock(String ip, long start, long end) {
            this.ip = ip;
            this.start = start;
            this.end = end;
            if(start < 0){
                System.out.println(ip);
            }
        }
    }

    /**
     * 简单网络 get 工具类
     * @param path
     * @return
     */
    private static String getText(String path){
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if(200 == urlConnection.getResponseCode()){
                //得到输入流
                InputStream is =urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
