package com.lyp.learn.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-04 15:37
 */
public class DichotomySearch3 {
    static  List<IpBlock> ipBlockList = new ArrayList<>();
    static{
        try {
            List<String> allLines =  Files.readAllLines(Paths.get(DichotomySearch3.class.getResource("/chinaIp.txt").toURI()));
            String [] ipArr = null;
            IpBlock ipBlock = null;
            for(String line : allLines){
                if(line.trim().startsWith("#")) {
                    continue;
                }
                //按空白字符分
                ipArr = line.split("\\s+");
                //按 tab 键分
                //ipArr = line.split("\\t");
                ipBlock = new IpBlock(ipArr[0],convertIpToLong(ipArr[0]),convertIpToLong(ipArr[1]));
                ipBlockList.add(ipBlock);
            }
            System.out.println(ipBlockList.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }


    public static void main(String[] args) {
       // System.out.println(DichotomySearch3.isChinaIp("114.114.114.114"));
        //System.out.println(DichotomySearch3.isChinaIp("8.8.8.8"));
        //System.out.println(IpAreaUtils.isInChina("114.114.114.114"));
        //System.out.println(IpAreaUtils.isInChina("8.8.8.8"));

        System.out.println(isChinaIp("1.0.1.0"));
        System.out.println(IpAreaUtils.isInChina("1.0.1.0"));


        System.out.println(isChinaIp("1.0.2.255"));
        System.out.println(IpAreaUtils.isInChina("1.0.2.255"));

        List<String> ipList = new ArrayList<>();
        StringBuilder sb = null;
        Random random = new Random();
        for(int i =1 ; i <= 1000000; i++){
            sb = new StringBuilder();
            for(int j = 0; j <= 3; j++){
                if(j != 3){
                    sb.append(random.nextInt(255)).append(".");
                }else{
                    sb.append(random.nextInt(255));
                }
            }
            ipList.add(sb.toString());
        }
        //System.out.println(ipList);
        int sum = 0;
        for(String s : ipList){
            if(IpAreaUtils.isInChina(s).toString().equals(isChinaIp(s)+"")){
               // System.out.println(s);
                //System.out.println(IpAreaUtils.isInChina(s));
                //System.out.println(isChinaIp(s));
                //System.out.println("*************");
                sum++;
            }else{
                System.out.println("ip not equal : " + s);
                System.out.println(IpAreaUtils.isInChina(s));
                System.out.println(isChinaIp(s));
                System.out.println("-------------");
            }
        }

        System.out.println("============");
        System.out.println(sum);

    }

    public static boolean isChinaIp(String ipStr) {
        Long ipLong = convertIpToLong(ipStr);
        int start = 0;
        int end = ipBlockList.size() - 1;
        IpBlock currentIpBlock = null;
        boolean flag = false;
        while (start <= end) {
            int middle = (start + end) / 2;
            currentIpBlock = ipBlockList.get(middle);
            if (ipLong >= currentIpBlock.start && ipLong <= currentIpBlock.end) {
                flag =  true;
                break;
            } else if (ipLong < currentIpBlock.start) {
                end = middle - 1;
            }else if(ipLong > currentIpBlock.end){
                start = middle + 1;
            }
        }
        return flag;
    }

    private static Long convertIpToLong(String ipStr) {
        String[] itemArr = ipStr.trim().split("\\.");
        Long ipLong = 0L;
        try {
            ipLong +=  (Long.parseLong(itemArr[0]) << 24 & 0xFF000000);
            ipLong +=  (Long.parseLong(itemArr[1]) << 16 & 0xFF0000);
            ipLong +=  (Long.parseLong(itemArr[2]) << 8 & 0xFF00);
            ipLong +=  (Long.parseLong(itemArr[3]) & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipLong;
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
        }
    }






}
