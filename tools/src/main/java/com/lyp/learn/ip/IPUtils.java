package com.lyp.learn.ip;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IPUtils {

    private static List<IpBlock> ipBlockList = null;

    static {
        // 读取文件
        try {
            List<String> lines = Files.readAllLines(Paths.get(IPUtils.class.getResource("ip_data.txt").toURI()));
            // 初始化 IpBlockList
            ipBlockList = new ArrayList<>(lines.size());
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



    // 二分查找法查到 IP 是否属于中国
    public static Boolean isInChina(String ip){
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
                if( !(startBlock.start <= ipLong && startBlock.end >= ipLong) && !(endBlock.start <= ipLong && endBlock.end >= ipLong)
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

}
