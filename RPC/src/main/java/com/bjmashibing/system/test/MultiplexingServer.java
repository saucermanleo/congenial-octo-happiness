package com.bjmashibing.system.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/22 0022 14:27
 */
public class MultiplexingServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private int port = 9090;
    private void init(){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port),20);
            serverSocketChannel.configureBlocking(false);
            SelectionKey register = serverSocketChannel.register(selector,0 );
            // netty 使用此方法来注册   根据selectkey的interestops为依据
            register.interestOps(SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        init();
        while(true){
            while(selector.select()>0){
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isAcceptable()){
                        accept(key);
                    }else if (key.isReadable()){
                        handler(key);
                    }
                }
            }
        }
    }

    private void handler(SelectionKey key)  {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        int read = 0;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel client = channel.accept();
        client.configureBlocking(false);
        client.register(selector,SelectionKey.OP_READ, ByteBuffer.allocateDirect(8192));
        System.out.println("client...port: " + client.socket().getPort());
    }

    public static void main(String[] args) {
        MultiplexingServer server = new MultiplexingServer();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
