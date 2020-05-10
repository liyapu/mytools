package com.lyp.learn.base.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-10 19:11
 */
public class TCPSocketClient {
    public static void main(String[] args) throws Exception {
        //创建客户端的 socket服务 Socket
        //在 socket指定传输数据的 目的ip和端口
        String ip = "127.0.0.1";
        int port = 50032;
        Socket client = new Socket(ip,port);


        // 通过 socket建立的通道输出数据
        //取得输出流，写数据
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("helle, this is tcp".getBytes());

        //释放资源
        client.close();
    }
}
