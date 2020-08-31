package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:31
 */
public class UserMoveToCmdHandle implements Handle<GameMsgProtocol.UserMoveToCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, Object msg) {
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (userId == null) {
            return;
        }
        GameMsgProtocol.UserMoveToCmd object = (GameMsgProtocol.UserMoveToCmd) msg;
        GameMsgProtocol.UserMoveToResult.Builder builder = GameMsgProtocol.UserMoveToResult.newBuilder();
        builder.setMoveToPosX(object.getMoveToPosX()).setMoveToPosY(object.getMoveToPosY()).setMoveUserId(userId);
        GameMsgHandler.CHANNELS.writeAndFlush(builder.build());
    }
}
