package com.bjmashibing.system.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/25 0025 13:57
 */
public class NettyClient {
    public void open() throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        NioSocketChannel nioSocketChannel = new NioSocketChannel();
        ChannelPipeline pipeline = nioSocketChannel.pipeline();
        pipeline.addLast(new Handler());
        ChannelFuture sync = nioEventLoopGroup.register(nioSocketChannel).sync();
        ChannelFuture sync1 = nioSocketChannel.connect(new InetSocketAddress("localhost", 9090)).sync();
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello server".getBytes());
        nioSocketChannel.writeAndFlush(byteBuf);
        sync1.channel().closeFuture().sync();
        //ByteBuf byteBuf1 = UnpooledByteBufAllocator.DEFAULT.(8,20);
        System.out.println("client over");
    }
}

