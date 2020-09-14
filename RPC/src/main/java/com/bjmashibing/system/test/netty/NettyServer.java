package com.bjmashibing.system.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/25 0025 11:50
 */
public class NettyServer {
    private NioEventLoopGroup register = new NioEventLoopGroup(1);

    public void open() throws InterruptedException {
        NioServerSocketChannel server = new NioServerSocketChannel();
        server.pipeline().addLast(new Accept(register, new Handler()));
        ChannelFuture sync = register.register(server).sync();
        server.bind(new InetSocketAddress(9090)).sync();

        sync.channel().closeFuture().sync();
    }
}


class Accept extends ChannelInboundHandlerAdapter {
    private NioEventLoopGroup register;
    private ChannelHandler handler;

    public Accept(NioEventLoopGroup register, ChannelHandler handler) {
        this.register = register;
        this.handler = handler;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        ;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NioSocketChannel client = (NioSocketChannel) msg;
        client.pipeline().addLast(handler);
        try {
            register.register(client).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@ChannelHandler.Sharable
class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
//        CharSequence charSequence = buf.getCharSequence(0,buf.readableBytes(), CharsetUtil.UTF_8);
//        System.out.println(charSequence);
//        ctx.writeAndFlush(buf);
        int size = buf.writerIndex();
        byte[] data = new byte[size];
        buf.getBytes(0, data);
        String dd = new String(data);
        System.out.println(dd);
        ctx.writeAndFlush(buf);
    }


}