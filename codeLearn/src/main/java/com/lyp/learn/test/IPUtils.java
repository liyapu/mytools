package com.lyp.learn.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-08 21:30
 */
public class IPUtils {


    public List<IpBlock> getIpBlockList(){
        List<IpBlock> ipBlockList = new ArrayList<>();
        // 读取文件
        try {
            Map<String,Map<Long,Long>> ipMaps = new HashMap<>();
            File file = new File(this.getClass().getResource("/data.txt").toURI());//定义一个file对象，用来初始化FileReader
            FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            String line = "";


            while ((line = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                String[] segments = line.split("\t"); //按tab分割
                String ipSegmentStart = segments[0].trim();
                String ipSegmentEnd = segments[1].trim();
                ipBlockList.add(new IpBlock( ipSegmentStart, getLong(ipSegmentStart),getLong(ipSegmentEnd)));
             }
            bReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return ipBlockList;
    }

    // 二分查找法查到 IP 是否属于中国
    public  Boolean isInChina(String ip){
        List<IpBlock> ipBlockList = new IPUtils().getIpBlockList();
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
                if( !(startBlock.start <= ipLong && startBlock.end >= ipLong) && !(endBlock.start <= ipLong && endBlock.end >= ipLong)){
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

    public static void main(String[] args) {
        System.out.println(new IPUtils().isInChina("1.183.176.184"));
    }
}
