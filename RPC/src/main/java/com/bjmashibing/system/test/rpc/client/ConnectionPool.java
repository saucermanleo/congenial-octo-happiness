package com.bjmashibing.system.test.rpc.client;

import com.bjmashibing.system.test.rpc.request.SmartCarDecoder;
import com.bjmashibing.system.test.rpc.request.SmartCarEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/28 0028 19:18
 */
public class ConnectionPool {
    private int size;
    private BlockingQueue<NioSocketChannel> blockingQueue;
    private int now;
    private Bootstrap bootstrap;
    private InetSocketAddress inetSocketAddress;

    public ConnectionPool(int size, InetSocketAddress inetSocketAddress) {
        this.size = size;
        this.blockingQueue = new ArrayBlockingQueue<>(size);
        this.inetSocketAddress = inetSocketAddress;
        bootstrap = new Bootstrap();
        NioEventLoopGroup loop = new NioEventLoopGroup(1);
        bootstrap.group(loop).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SmartCarEncoder());
                ch.pipeline().addLast(new SmartCarDecoder());
                // 处理网络IO
                ch.pipeline().addLast(new ClientHandler());
            }
        });
    }

    public NioSocketChannel createConnection() throws InterruptedException {
        NioSocketChannel channel = (NioSocketChannel)bootstrap.connect(inetSocketAddress).sync().channel();
        System.out.println("tcp连接数:"+(now+1));
        return channel;
    }


    public  NioSocketChannel getConnection() throws InterruptedException {
        NioSocketChannel nioSocketChannel;

        if(blockingQueue.isEmpty()){

            if(now<size){
                synchronized (this){
                    if(now<size) {
                        nioSocketChannel = createConnection();
                        blockingQueue.add(nioSocketChannel);
                        now++;
                    }
                }

            }
        }
        nioSocketChannel = blockingQueue.take();
        return nioSocketChannel;
    }

    public  void close(NioSocketChannel nioSocketChannel){
        blockingQueue.add(nioSocketChannel);
    }
}
