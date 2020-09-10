package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.User;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:18
 */
public class UserEntryCmdHandle implements Handle<GameMsgProtocol.UserEntryCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, Object msg) {
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == userId) {
            return;
        }

        // 获取已有用户
        User existUser = GameMsgHandler.users.get(userId);
        if (null == existUser) {
            return;
        }

        // 获取英雄形象
        String heroAvatar = existUser.getRole();

        GameMsgProtocol.UserEntryResult.Builder resultBuilder = GameMsgProtocol.UserEntryResult.newBuilder();
        resultBuilder.setUserId(userId);
        resultBuilder.setHeroAvatar(heroAvatar);
        resultBuilder.setUserName(existUser.name);

        GameMsgHandler.CHANNELS.writeAndFlush(resultBuilder.build());
    }
}
