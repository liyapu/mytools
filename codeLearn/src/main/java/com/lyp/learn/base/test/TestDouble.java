package com.lyp.learn.base.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-08 21:52
 */
public class TestDouble {

    public List<IPUtils.IpBlock> getIpBlockList(){
        List<IPUtils.IpBlock> ipBlockList = new ArrayList<>();
        try {
            File file = new File(this.getClass().getResource("/ipppp.txt").toURI());
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            String line = "";


            while ((line = bReader.readLine()) != null) {
                String[] segments = line.split("\t");
                String ipSegmentStart = segments[0].trim();
                String ipSegmentEnd = segments[1].trim();
                ipBlockList.add(new IPUtils.IpBlock( ipSegmentStart, getLong(ipSegmentStart),getLong(ipSegmentEnd)));
            }
            bReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return ipBlockList;
    }

    // 二分查找法查到 IP 是否属于中国
    public  Boolean isInChina(String ip){
        List<IPUtils.IpBlock> ipBlockList = new IPUtils().getIpBlockList();
        int start = 0, end = ipBlockList.size()-1;
        int index = 0;
        long ipLong = getLong(ip);

        while(true){
            index = (start + end) / 2;
            IPUtils.IpBlock current = ipBlockList.get(index);

            if(current.start <= ipLong && current.end >= ipLong){
                return true;
            }

            if(end - start <= 1){
                IPUtils.IpBlock startBlock = ipBlockList.get(start);
                IPUtils.IpBlock endBlock = ipBlockList.get(end);
                if(
                        !(startBlock.start <= ipLong && startBlock.end >= ipLong) && !(endBlock.start <= ipLong && endBlock.end >= ipLong)
                        ){
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

    //////////////////////////////////////////

    private boolean inChinaOfIp(String ipStr){
        Map<String,Map<Long,Long>> ipMap = null;
        try {
            ipMap = new TestDouble().getIpMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null == ipMap){
            System.out.println("error========================");
        }
        //System.out.println(ipMap.size());
        Long sum = 0L;
        for(Map.Entry<String,Map<Long,Long>> entry : ipMap.entrySet()){
            Map<Long,Long> ips = entry.getValue();
            for(Map.Entry<Long,Long> ent : ips.entrySet()){
                sum++;
            }
        }
        //System.out.println("sum=" + sum);
        boolean flag = false;
        String ipStrTrim = ipStr.trim();
        String [] ipStrArr = ipStrTrim.split("\\.");
        String checkPrefixNum = ipStrArr[0];
        //System.out.println("checkNum = " + checkPrefixNum);
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

    //////////////////////////////////////////
    public static void main(String[] args) {
      //  System.out.println(new TestDouble().isInChina("1.183.176.184"));
      //  System.out.println(new TestDouble().inChinaOfIp("1.183.176.184"));
        List<String> ipList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i <= 10000; i++){
            ipList.add(random.nextInt(255) + "."+ random.nextInt(255) + "."+ random.nextInt(255) + "." + random.nextInt(255));
        }



//        Long startTime = System.currentTimeMillis();
//        for(String ip : ipList){
//            new TestDouble().isInChina(ip);
//        }
//        System.out.println("aa" + (System.currentTimeMillis()-startTime));
//
//        long st = System.currentTimeMillis();
//        for(String ip : ipList){
//            new TestDouble().inChinaOfIp(ip);
//        }
//        System.out.println("bb"+ (System.currentTimeMillis()-st));
        boolean isInChina;
        boolean inChinaOfIp ;
        for(String ip : ipList){
            isInChina = new TestDouble().isInChina(ip);
            inChinaOfIp  = new TestDouble().inChinaOfIp(ip);
            if(isInChina == inChinaOfIp){
                //System.out.println("----");
            }else{
                System.out.println("isInChina = " + isInChina);
                System.out.println("inChinaOfIp = " + inChinaOfIp);
                System.out.println("ip=" + ip);
                System.out.println("============================");
            }
        }


    }
}
