package com.lyp.learn.base.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-04 15:37
 */
public class ChinaIpUtil2 {
    static  List<IpBlock> ipBlockList = new ArrayList<>();
    //匹配以 空白字符# 开头的
    static Pattern pattern = Pattern.compile("^\\s*#.*");

    static{
        try {
            List<String> allLines =  Files.readAllLines(Paths.get(ChinaIpUtil2.class.getResource("/chinaIp.txt").toURI()));
            String [] ipArr = null;
            IpBlock ipBlock = null;
            for(String line : allLines){
                if(pattern.matcher(line).matches()){
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



    public static boolean isInChina(String ipStr) {
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
