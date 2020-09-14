package com.bjmashibing.system.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/20 0020 17:46
 */
public class NIOServer {
    public static void main(String[] args) {
        List<SocketChannel> clients = new LinkedList<>();
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(9090), 20);
            serverSocketChannel.configureBlocking(false);
            //serverSocketChannel.setOption(StandardSocketOptions.TCP_NODELAY, false);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//        StandardSocketOptions.TCP_NODELAY
//        StandardSocketOptions.SO_KEEPALIVE
//        StandardSocketOptions.SO_LINGER
//        StandardSocketOptions.SO_RCVBUF
//        StandardSocketOptions.SO_SNDBUF
//        StandardSocketOptions.SO_REUSEADDR
            while (true) {
                SocketChannel accept = serverSocketChannel.accept();
                if (accept == null) {
                    System.out.println("null....");
                } else {
                    accept.configureBlocking(false);
                    accept.setOption(StandardSocketOptions.TCP_NODELAY, false);
                    int port = accept.socket().getPort();
                    System.out.println("client...port: " + port);
                    clients.add(accept);
                }

                for (SocketChannel c : clients) {
                    if (c.read(buffer) > 0) {
                        buffer.flip();
                        byte[] aaa = new byte[buffer.limit()];
                        buffer.get(aaa);

                        String b = new String(aaa);
                        System.out.println(c.socket().getPort() + " : " + b);
                        buffer.clear();
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
