package com.lyp.learn.base.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-08 18:17
 */
public class testIp {
    public static void main(String[] args) {
        System.out.println(new testIp().inChinaOfIp("1.183.176.184"));

    }

    private boolean inChinaOfIp(String ipStr){
        Map<String,Map<Long,Long>> ipMap = null;
        try {
            ipMap = new testIp().getIpMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null == ipMap){
            System.out.println("error========================");
        }
        System.out.println(ipMap.size());
        Long sum = 0L;
        for(Map.Entry<String,Map<Long,Long>> entry : ipMap.entrySet()){
            Map<Long,Long> ips = entry.getValue();
            for(Map.Entry<Long,Long> ent : ips.entrySet()){
                sum++;
            }
        }
        System.out.println("sum=" + sum);
        boolean flag = false;
        String ipStrTrim = ipStr.trim();
        String [] ipStrArr = ipStrTrim.split("\\.");
        String checkPrefixNum = ipStrArr[0];
        System.out.println("checkNum = " + checkPrefixNum);
        Set<String> ipKeys = ipMap.keySet();
        if(!ipKeys.contains(checkPrefixNum)){
            return false;
        }
        Long checkIpNum = ipToLong(ipStrTrim);
        Map<Long,Long> ipSegmentMap = ipMap.get(checkPrefixNum);
        for(Map.Entry<Long,Long> entry : ipSegmentMap.entrySet()){
            if(checkIpNum >= entry.getKey() && checkIpNum <= entry.getValue()){
                flag =  true;
                break;
            }
        }
        return flag;
    }

    private Map<String,Map<Long,Long>> getIpMap() throws Exception {
        Map<String,Map<Long,Long>> ipMaps = new HashMap<>();
        File file = new File(this.getClass().getResource("/ipppp.txt").toURI());//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        String line = "";


        while ((line = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            String[] segments = line.split("\t"); //按tab分割
            String ipSegmentStart = segments[0].trim();
            String ipSegmentEnd = segments[1].trim();
            String [] segmentStartArr = ipSegmentStart.split("\\.");
            String startNum = segmentStartArr[0];
            if(ipMaps.containsKey(startNum)){
                Map<Long,Long> ipLongMap = ipMaps.get(startNum);
                ipLongMap.put(ipToLong(ipSegmentStart),ipToLong(ipSegmentEnd));
            }else{
                Map<Long,Long> ipLongMap = new HashMap<>();
                ipLongMap.put(ipToLong(ipSegmentStart),ipToLong(ipSegmentEnd));
                ipMaps.put(startNum,ipLongMap);
            }

        }
        bReader.close();
        return ipMaps;
    }

    /**
      * ip地址转成long型数字
      * 将IP地址转化成整数的方法如下：
      * 1、通过String的split方法按.分隔得到4个长度的数组
      * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
      * @param strIp
      * @return
      */
    public static long ipToLong(String ipStr){
        String [] ipArr = ipStr.split("\\.");
        return (Long.parseLong(ipArr[0]) << 24)  + (Long.parseLong(ipArr[1]) << 16) + (Long.parseLong(ipArr[2]) << 8) + Long.parseLong(ipArr[3]);
    }
}
