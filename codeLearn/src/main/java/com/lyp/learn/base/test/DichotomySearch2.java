package com.lyp.learn.base.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-04 15:37
 */
public class DichotomySearch2 {
    List<IpBlock> IpBlockList = null;
    static{
        try {
            String ipDataPath = DichotomySearch2.class.getClassLoader().getResource("ip.txt").getPath();
            System.out.println(ipDataPath);
           // List<String> lines = Files.readAllLines(Paths.get(ipDataPath));
           // List<String> lines = Files.readAllLines(Paths.get("chinaIp.txt"), Charset.forName("gbk"));

           // List<String> lines = Files.readAllLines(Paths.get(ipDataPath), StandardCharsets.UTF_8);

            List<String> allLines =  Files.readAllLines(Paths.get(DichotomySearch2.class.getResource("/ip.txt").toURI()));

            // List<String> lines = Files.readAllLines(Paths.get(ipDataPath), Charset.forName("UTF-8"));
           // System.out.println(lines.size());
            System.out.println(allLines.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[] { 12, 23, 34, 45, 56, 67, 77, 89, 90 };
        System.out.println(search(arr, 12));
        System.out.println(search(arr, 45));
        System.out.println(search(arr, 67));
        System.out.println(search(arr, 89));
        System.out.println(search(arr, 99));
    }

    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private static Long convertIp(String ipStr) {
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
