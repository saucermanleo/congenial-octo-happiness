package org.zy.tinygame.handler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.zy.tinygame.GameMsgProtocol;

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


        switch (msgCode) {
            case GameMsgProtocol.MsgCode.USER_ENTRY_CMD_VALUE:
                cmd = GameMsgProtocol.UserEntryCmd.parseFrom(bytes);
                break;
            case GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_CMD_VALUE:
                cmd = GameMsgProtocol.WhoElseIsHereCmd.parseFrom(bytes);
                break;
            case GameMsgProtocol.MsgCode.USER_MOVE_TO_CMD_VALUE:
                cmd = GameMsgProtocol.UserMoveToCmd.parseFrom(bytes);
                break;
        }

        if (null != cmd) {
            ctx.fireChannelRead(cmd);
        }

    }
}
