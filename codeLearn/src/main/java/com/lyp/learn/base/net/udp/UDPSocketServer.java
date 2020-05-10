package com.lyp.learn.base.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-10 18:53
 */
public class UDPSocketServer {
    public static void main(String[] args) throws Exception {
       //创建服务器端 socket 对象 DatagramSocket
        InetAddress address = Inet4Address.getLocalHost();
        int port = 50031;
        DatagramSocket server = new DatagramSocket(port);

        //创建接收数据的数据包(数据接收的容器)
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
        //接收数据
        server.receive(packet);

        //针对数据包进行解析
        String hostAddress = packet.getAddress().getHostAddress();
        byte[] data = packet.getData();
        System.out.println("接收来自 ：" + hostAddress);
        System.out.println("接收内容 : " + new String(data));

        //释放资源
        server.close();
    }
}


























