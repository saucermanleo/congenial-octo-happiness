package com.bjmashibing.system.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/25 0025 15:20
 */
public class BootStrapTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
//        Bootstrap bootStrap = new Bootstrap();
//        ChannelFuture connect = bootStrap.group(nioEventLoopGroup).channel(NioSocketChannel.class).handler(new Handler())
//                .connect(new InetSocketAddress("192.168.25.128", 9090));
//        Channel channel = connect.sync().channel();
//        ByteBuf byteBuf = Unpooled.copiedBuffer("hello server".getBytes());
//        channel.writeAndFlush(byteBuf).sync();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = serverBootstrap.group(nioEventLoopGroup, nioEventLoopGroup).channel(NioServerSocketChannel.class).childHandler(new Handler())
                .bind(new InetSocketAddress(9090)).sync();
        channelFuture.channel().closeFuture().sync();
        System.out.println("transfer");

    }
}
