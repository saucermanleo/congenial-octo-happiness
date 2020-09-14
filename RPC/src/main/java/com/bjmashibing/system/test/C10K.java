package com.bjmashibing.system.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/20 0020 16:23
 */
public class C10K {
    public static void main(String[] args) throws IOException {
        List<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress severAddr = new InetSocketAddress("localhost",9090);
        for(int i=10000;i<65535;i++){
//            SocketChannel socketChannel = SocketChannel.open();
//            socketChannel.bind(new InetSocketAddress("172.16.121.179",i));
//            socketChannel.connect(severAddr);
//            socketChannel.isOpen();
//            clients.add(socketChannel);

            SocketChannel client = SocketChannel.open();
            client.bind(new InetSocketAddress("localhost",i));
            client.connect(severAddr);
            client.isOpen();
            clients.add(client);
        }
        System.out.println(clients.size());
    }
}
