package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.User;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:27
 */
public class WhoElseIsHereCmdHandle implements Handle<GameMsgProtocol.WhoElseIsHereCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, Object msg) {
        GameMsgProtocol.WhoElseIsHereCmd object = this.transfer(msg);
        GameMsgProtocol.WhoElseIsHereResult.Builder builder = GameMsgProtocol.WhoElseIsHereResult.newBuilder();
        for (User value : GameMsgHandler.users.values()) {
            builder.addUserInfo(GameMsgProtocol.WhoElseIsHereResult.UserInfo.newBuilder().setUserId(value.getUserId()).setHeroAvatar(value.getRole()).build());
        }
        ctx.channel().writeAndFlush(builder.build());
    }
}
