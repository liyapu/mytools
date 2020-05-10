package com.lyp.learn.base.net.udp;

import java.net.*;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-10 18:53
 */
public class UDPSocketClient {
    public static void main(String[] args) throws Exception {
        //创建发送端的 socket对象  DatagramSocket
        DatagramSocket client = new DatagramSocket();

        //把发送的数据打包
        String message = "hello udp";
        byte[] bytes = message.getBytes();
        InetAddress address = Inet4Address.getLocalHost();
        int port = 50031;
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length,address,port);

        //发送打包好的数据
        client.send(packet);
        //释放资源
        client.close();
    }
}


























