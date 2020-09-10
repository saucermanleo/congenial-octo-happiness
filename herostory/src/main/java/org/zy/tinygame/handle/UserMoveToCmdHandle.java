package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.MoveState;
import org.zy.tinygame.User;
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

        User user = GameMsgHandler.users.get(userId);

        GameMsgProtocol.UserMoveToCmd object = (GameMsgProtocol.UserMoveToCmd) msg;

        user.moveState.fromPosX = object.getMoveFromPosX();
        user.moveState.fromPosY = object.getMoveFromPosY();
        user.moveState.toPosX = object.getMoveToPosX();
        user.moveState.toPosY = object.getMoveToPosY();
        user.moveState.startTime = System.currentTimeMillis();

        MoveState mvState =user.moveState;
        GameMsgProtocol.UserMoveToResult.Builder resultBuilder = GameMsgProtocol.UserMoveToResult.newBuilder();
        resultBuilder.setMoveUserId(userId);
        resultBuilder.setMoveFromPosX(mvState.fromPosX);
        resultBuilder.setMoveFromPosY(mvState.fromPosY);
        resultBuilder.setMoveToPosX(mvState.toPosX);
        resultBuilder.setMoveToPosY(mvState.toPosY);
        resultBuilder.setMoveStartTime(mvState.startTime);
        GameMsgHandler.CHANNELS.writeAndFlush(resultBuilder.build());
    }
}
