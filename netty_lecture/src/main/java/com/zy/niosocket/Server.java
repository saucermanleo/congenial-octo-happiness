package com.zy.niosocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static Set<SocketChannel> sets = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int[] ports = {5000, 5001, 5002, 5003, 5004};
        Selector selector = Selector.open();
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);
            serverSocket.bind(inetSocketAddress);

            System.out.println("listening port:" + ports[i]);
        }

        while (true) {
            //This method performs a blocking
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    sets.add(socketChannel);
                    System.out.println("get client connect " + socketChannel);

                }

                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    InetSocketAddress address = (InetSocketAddress) socketChannel.getRemoteAddress();

                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    Charset charset = Charset.forName("utf-8");
                    String m = address.getHostName() + ":" + address.getPort()+ ":"  + String.valueOf(charset.decode(byteBuffer).array());
                    System.out.println(m);
                    sets.forEach(x -> {
                        try {
                            if(x != socketChannel) {
                                byteBuffer.clear();
                                byteBuffer.put(m.getBytes());
                                byteBuffer.flip();
                                x.write(byteBuffer);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });


                }
                iterator.remove();

            }

        }


    }
}
