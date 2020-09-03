package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/3 0003 10:48
 */
public class UserAttkCmdHandle implements Handle<GameMsgProtocol.UserAttkCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, Object msg) {
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (userId == null) {
            return;
        }
        GameMsgProtocol.UserAttkCmd transfer = this.transfer(msg);

        GameMsgProtocol.UserAttkResult.Builder builder = GameMsgProtocol.UserAttkResult.newBuilder();
        GameMsgProtocol.UserAttkResult build = builder.setAttkUserId(userId).setTargetUserId(transfer.getTargetUserId()).build();
        GameMsgHandler.CHANNELS.writeAndFlush(build);

        GameMsgProtocol.UserSubtractHpResult build1 = GameMsgProtocol.UserSubtractHpResult.newBuilder().setSubtractHp(10).setTargetUserId(transfer.getTargetUserId()).build();
        GameMsgHandler.CHANNELS.writeAndFlush(build1);


    }
}
