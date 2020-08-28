package org.zy.tinygame;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.zy.tinygame.handler.GameMsgDecoder;
import org.zy.tinygame.handler.GameMsgEncoder;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/28 0028 11:04
 */
public class ServerMain {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup works = new NioEventLoopGroup(1);
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = bootstrap.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new HttpServerCodec())
                        .addLast(new HttpObjectAggregator(65535))
                        .addLast(new WebSocketServerProtocolHandler("/websocket"))
                        .addLast(new GameMsgDecoder())
                        .addLast(new GameMsgHandler(),new GameMsgEncoder());

            }
        }).group(boss, works).bind(12345);
        try {
            channelFuture.sync();
            if (channelFuture.isSuccess()) {
                System.out.println("服务器启动成功");
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            works.shutdownGracefully();
        }
    }
}
