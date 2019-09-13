package com.lyp.learn.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: liyapu
 * @Description: 判断ip 是否属于中国的工具类
 * @create: 2019-01-04 15:37
 */
@Slf4j
public class ChinaIpUtilNovel {
    static  List<IpBlock> ipBlockList = new ArrayList<>();
    //匹配以 空白字符# 开头的
    static Pattern pattern = Pattern.compile("^\\s*#.*");


    static{
        try( InputStream stream = ChinaIpUtil.class.getClassLoader().getResourceAsStream("chinaIp.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"))) {

            String [] ipArr = null;
            IpBlock ipBlock = null;
            String line = null;

            while ((line = br.readLine()) != null) {
                if(pattern.matcher(line).matches()){
                    continue;
                }
                ipArr = line.split("\\s+");
                ipBlock = new IpBlock(ipArr[0],convertIpToLong(ipArr[0]),convertIpToLong(ipArr[1]));
                ipBlockList.add(ipBlock);
            }
            log.info("ChinaIpUtil load ip success.size:{}",ipBlockList.size());
        } catch (Exception e) {
            log.info("ChinaIpUtil load ip  err.",e);
        }
    }

    public static void main(String[] args) {
        System.out.println(isInChina("8.8.8.8"));
        System.out.println(isInChina("114.114.114.114"));

    }

    /**
     * 二分法查找，判断ip是否属于中国
     * @param ipStr
     * @return
     */
    public static boolean isInChina(String ipStr){
        if(ListUtils.isEmpty(ipBlockList) || StringUtils.isBlank(ipStr)){
            log.warn("ChinaIpUtil isInChina something is empty.ipBlockListSize:{},ipStr:{}",ipBlockList.size(),ipStr);
            return true;
        }
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
