package org.zy.tinygame.handler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.zy.tinygame.GameMsgRecognizer;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/28 0028 15:28
 */
public class GameMsgEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (null == msg ||
                !(msg instanceof GeneratedMessageV3)) {
            super.write(ctx, msg, promise);
            return;
        }
        BinaryWebSocketFrame webSocketFrame = null;
        int msgCode = GameMsgRecognizer.getMsgCode(msg);
        byte[] bytes = ((GeneratedMessageV3) msg).toByteArray();
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeInt(msgCode);
        buffer.writeBytes(bytes);
        webSocketFrame = new BinaryWebSocketFrame(buffer);
        super.write(ctx, webSocketFrame, promise);
    }
}
