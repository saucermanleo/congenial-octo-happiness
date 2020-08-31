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
    public void handle(ChannelHandlerContext ctx,Object msg) {
        GameMsgProtocol.UserEntryCmd userEntryCmd =(GameMsgProtocol.UserEntryCmd) msg;

        GameMsgProtocol.UserEntryResult.Builder builder = GameMsgProtocol.UserEntryResult.newBuilder();
        builder.setUserId(userEntryCmd.getUserId()  );
        builder.setHeroAvatar(userEntryCmd.getHeroAvatar());

        User user = new User();
        user.setUserId(userEntryCmd.getUserId());
        user.setRole(userEntryCmd.getHeroAvatar());
        GameMsgHandler.users.put(user.getUserId(),user);

        ctx.channel().attr(AttributeKey.valueOf("userId")).set(user.getUserId());

        GameMsgHandler.CHANNELS.writeAndFlush(builder.build());
    }
}
