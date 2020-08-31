package org.zy.tinygame.handler;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.GameMsgRecognizer;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/28 0028 13:57
 */
public class GameMsgDecoder extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        ByteBuf content = msg.content();
        content.readShort();
        short msgCode = content.readShort();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        GeneratedMessageV3 cmd = null;

        Message.Builder builder = GameMsgRecognizer.getBuilder(msgCode);

        if (null != builder) {
            builder.clear();
            ctx.fireChannelRead(builder.mergeFrom(bytes).build());
        }

    }
}
