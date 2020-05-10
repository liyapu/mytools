package com.lyp.learn.base.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-10 19:11
 */
public class TCPSocketServer {
    public static void main(String[] args) throws Exception {
        //建立服务端socket服务， ServerSocket 并且监听本机的一个端口
        String ip = "127.0.0.1";
        int port = 50032;

        ServerSocket server = new ServerSocket(port);

        //监听连接
        // 阻塞方法，有请求过来，返回与之对应的 socket来通信
        Socket socket = server.accept();

        //获取输入流，读取数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];

        int len = inputStream.read(bytes);
        System.out.println(new String(bytes,0,len));

        //释放资源
        server.close();

    }
}





















