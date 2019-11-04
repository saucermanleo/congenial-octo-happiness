package com.zy.niosocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 5000));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach((x) -> {
                try {
                    if (x.isConnectable()) {
                        if (socketChannel.isConnectionPending()) {
                            socketChannel.finishConnect();
                        }
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        byteBuffer.clear();
                        byteBuffer.put((LocalDateTime.now() + ":连接完成").getBytes());
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);

                        new Thread(()->{
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                            ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
                            while(true){
                                try {
                                    byteBuffer1.clear();
                                    byteBuffer1.put( bufferedReader.readLine().getBytes());
                                    byteBuffer1.flip();
                                    socketChannel.write(byteBuffer1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();



                    } else if (x.isReadable()) {
                        byteBuffer.clear();
                        int count = socketChannel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array(),0,count));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            selectionKeys.clear();


        }


    }
}
