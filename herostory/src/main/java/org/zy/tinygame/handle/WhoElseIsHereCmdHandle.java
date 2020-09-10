package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.MoveState;
import org.zy.tinygame.User;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:27
 */
public class WhoElseIsHereCmdHandle implements Handle<GameMsgProtocol.WhoElseIsHereCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, Object msg) {
        GameMsgProtocol.WhoElseIsHereResult.Builder resultBuilder = GameMsgProtocol.WhoElseIsHereResult.newBuilder();

        for (User currUser : GameMsgHandler.users.values()) {
            if (null == currUser) {
                continue;
            }

            // 在这里构建每一个用户的信息
            GameMsgProtocol.WhoElseIsHereResult.UserInfo.Builder userInfoBuilder = GameMsgProtocol.WhoElseIsHereResult.UserInfo.newBuilder();
            userInfoBuilder.setUserId(currUser.getUserId());
            userInfoBuilder.setHeroAvatar(currUser.getRole());
            userInfoBuilder.setUserName(currUser.name);

            // 构建移动状态
            MoveState mvState = currUser.moveState;
            GameMsgProtocol.WhoElseIsHereResult.UserInfo.MoveState.Builder
                    mvStateBuilder = GameMsgProtocol.WhoElseIsHereResult.UserInfo.MoveState.newBuilder();
            mvStateBuilder.setFromPosX(mvState.fromPosX);
            mvStateBuilder.setFromPosY(mvState.fromPosY);
            mvStateBuilder.setToPosX(mvState.toPosX);
            mvStateBuilder.setToPosY(mvState.toPosY);
            mvStateBuilder.setStartTime(mvState.startTime);
            // 将移动状态设置给用户信息
            userInfoBuilder.setMoveState(mvStateBuilder.build());

            // 将用户信息添加到结果消息
            resultBuilder.addUserInfo(userInfoBuilder);
        }

        GameMsgProtocol.WhoElseIsHereResult newResult = resultBuilder.build();
        ctx.channel().writeAndFlush(newResult);
    }
}
