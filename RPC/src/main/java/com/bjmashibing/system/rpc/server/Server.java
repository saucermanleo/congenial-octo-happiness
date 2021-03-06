package com.bjmashibing.system.rpc.server;

import com.bjmashibing.system.rpc.anotation.RPCInstance;
import com.bjmashibing.system.rpc.anotation.RPCInterface;
import com.bjmashibing.system.rpc.request.SmartCarDecoder;
import com.bjmashibing.system.rpc.request.SmartCarEncoder;
import com.bjmashibing.system.rpc.util.ClassReactUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:25
 */
public class Server {

    public static BeansMange defaultBeanManage = new DefaultBeanManage();

    private int port;
    private Class<?> clazz;

    public Server(int port, Class<?> clazz) {
        this.port = port;
        this.clazz = clazz;
    }


    public Server(int port) {
        this.port = port;
        try {
            startServer(port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            Set<Class<?>> classes = ClassReactUtil.listClazz(clazz, true, x -> {
                RPCInstance declaredAnnotation = x.getDeclaredAnnotation(RPCInstance.class);
                if (declaredAnnotation != null) {
                    return true;
                }
                return false;
            });

            for (Class<?> aClass : classes) {
                for (Class genericInterface : aClass.getInterfaces()) {
                    if (genericInterface.isAnnotationPresent(RPCInterface.class)) {
                        defaultBeanManage.put(genericInterface, aClass.newInstance());
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        this.init();
        try {
            this.startServer(this.port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startServer(int port) throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(nioEventLoopGroup, nioEventLoopGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SmartCarEncoder());
                ch.pipeline().addLast(new SmartCarDecoder());
                // 处理网络IO
                ch.pipeline().addLast(new ServerHandler());
            }
        })
                .bind(new InetSocketAddress(this.port)).sync();
    }
}
