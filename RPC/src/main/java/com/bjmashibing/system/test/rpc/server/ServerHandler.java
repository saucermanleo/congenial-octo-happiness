package com.bjmashibing.system.test.rpc.server;

import com.bjmashibing.system.test.rpc.request.Parameter;
import com.bjmashibing.system.test.rpc.request.SmartCarProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:32
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 用于获取客户端发来的数据信息
        SmartCarProtocol body = (SmartCarProtocol) msg;

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(body.getContent());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        Parameter o = (Parameter)objectInputStream.readObject();

        Class<?> aClass = Server.beans.get(o.getClassName()).getClass();
        Method method = aClass.getMethod(o.getMethodName(), o.getParameterTypes());
        Object invoke = method.invoke(Server.beans.get(o.getClassName()), o.getArgs());

        o.setResult(invoke);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(o);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        body.setContentLength(bytes.length);
        body.setContent(bytes);
        ctx.writeAndFlush(body).sync();
    }
}

