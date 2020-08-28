package org.zy.tinygame.handler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.zy.tinygame.GameMsgProtocol;

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
        int msgCode = -1;
        if (msg instanceof GameMsgProtocol.UserEntryResult) {
            msgCode = GameMsgProtocol.MsgCode.USER_ENTRY_RESULT_VALUE;
        }else if(msg instanceof GameMsgProtocol.WhoElseIsHereResult){
            msgCode = GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_RESULT_VALUE;
        }
        else if(msg instanceof GameMsgProtocol.UserMoveToResult){
            msgCode = GameMsgProtocol.MsgCode.USER_MOVE_TO_RESULT_VALUE;
        }else if (msg instanceof GameMsgProtocol.UserQuitResult) {
            msgCode = GameMsgProtocol.MsgCode.USER_QUIT_RESULT_VALUE;
        }
        byte[] bytes = ((GeneratedMessageV3) msg).toByteArray();
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeInt(msgCode);
        buffer.writeBytes(bytes);
        webSocketFrame = new BinaryWebSocketFrame(buffer);
        super.write(ctx, webSocketFrame, promise);
    }
}
