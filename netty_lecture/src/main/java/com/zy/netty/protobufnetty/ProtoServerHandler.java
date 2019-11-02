package com.zy.netty.protobufnetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.Student msg) throws Exception {
        System.out.println(msg.getId());
        System.out.println(msg.getName());
        System.out.println(msg.getEmail());
    }
}
