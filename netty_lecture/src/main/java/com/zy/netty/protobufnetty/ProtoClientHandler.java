package com.zy.netty.protobufnetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoClientHandler extends SimpleChannelInboundHandler<AddressBookProtos.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AddressBookProtos.Student student = AddressBookProtos.Student.newBuilder().setEmail("1006180871@qq.com").setId(10086).setName("zy").build();
        ctx.channel().writeAndFlush(student);
    }
}
