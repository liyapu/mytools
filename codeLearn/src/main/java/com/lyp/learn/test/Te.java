package com.lyp.learn.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-10-08 18:17
 */
public class Te {
    public static void main(String[] args) {
        boolean result = new Te().isValidRange("192.168.4.1", "192.168.45.112", "192.168.42.25");
        System.out.println(result);
        try {
            long ipLo = new Te().ipTolong(InetAddress.getByName("162.168.4.1"));
            System.out.println(ipLo);
            System.out.println(new Te().ipToLongLong("162.168.4.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public boolean isValidRange(String ipStart, String ipEnd, String ipToCheck) {

        try {
            long ipLo = ipTolong(InetAddress.getByName(ipStart));
            long ipHi = ipTolong(InetAddress.getByName(ipEnd));
            long ipcheck = ipTolong(InetAddress.getByName(ipToCheck));

            return (ipcheck > ipLo && ipcheck < ipHi);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public long ipTolong(InetAddress ip) {
        long result = 0;

        byte[] ipAdds = ip.getAddress();

        for (byte b : ipAdds) {
            result <<= 8;
            result |= b & 0xff;
        }

        return result;
    }

    /**
      * ip地址转成long型数字
      * 将IP地址转化成整数的方法如下：
      * 1、通过String的split方法按.分隔得到4个长度的数组
      * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
      * @param strIp
      * @return
      */
    public static long ipToLongLong(String strIp){
        String[]ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24)  + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }
}
