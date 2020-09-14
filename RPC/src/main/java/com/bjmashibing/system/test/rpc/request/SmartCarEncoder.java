package com.bjmashibing.system.test.rpc.request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 10:35
 */
public class SmartCarEncoder extends MessageToByteEncoder<SmartCarProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SmartCarProtocol msg, ByteBuf out) throws Exception {
        // 写入消息SmartCar的具体内容
        // 1.写入消息的开头的信息标志(int类型)
        out.writeInt(msg.getHead_data());
        // 2.写入消息的长度(int 类型)
        out.writeInt(msg.getContentLength());
        // 3.写入消息的内容(byte[]类型)
        out.writeBytes(msg.getContent());
    }
}
