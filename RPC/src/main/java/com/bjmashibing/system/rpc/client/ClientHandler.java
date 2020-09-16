package com.bjmashibing.system.rpc.client;

import com.bjmashibing.system.rpc.request.Parameter;
import com.bjmashibing.system.rpc.request.SmartCarProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 10:52
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 用于获取客户端发来的数据信息
        SmartCarProtocol body = (SmartCarProtocol) msg;

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(body.getContent());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        Parameter o = (Parameter)objectInputStream.readObject();
        Invocation runnable =(Invocation) RPCProxy.map.get(o.getRequestId());
        runnable.setResult(o.getResult());
        runnable.run();
    }
}
