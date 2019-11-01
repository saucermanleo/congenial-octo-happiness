package com.zy.netty.idle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class IdleServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
            String result = "";
            switch (idleStateEvent.state()){
                case READER_IDLE:
                    result = "读空闲";
                    break;
                case WRITER_IDLE:
                    result = "写空闲";
                    break;
                case ALL_IDLE:
                    result = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"超时事件:"+result);
            ctx.channel().close();
        }
    }
}
