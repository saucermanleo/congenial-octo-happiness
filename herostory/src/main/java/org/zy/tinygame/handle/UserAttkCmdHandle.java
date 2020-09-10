package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.User;
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


        User user = GameMsgHandler.users.get(transfer.getTargetUserId());
        if(user == null){
            return;
        }
        GameMsgHandler.executors.get(user.getUserId()).execute(()->{
            System.out.println(Thread.currentThread());
            GameMsgProtocol.UserAttkResult.Builder builder = GameMsgProtocol.UserAttkResult.newBuilder();
            GameMsgProtocol.UserAttkResult build = builder.setAttkUserId(userId).setTargetUserId(transfer.getTargetUserId()).build();
            GameMsgHandler.CHANNELS.writeAndFlush(build);


            int subtractHp = 10;
            user.blood = user.blood - subtractHp;

            // 广播减血消息
            broadcastSubtractHp(user.getUserId(), subtractHp);

            if (user.blood <= 0) {
                // 广播死亡消息
                broadcastDie(user.getUserId());
            }
        });
    }

    /**
     * 广播减血消息
     *
     * @param targetUserId 被攻击者 Id
     * @param subtractHp   减血量
     */
    static private void broadcastSubtractHp(int targetUserId, int subtractHp) {
        if (targetUserId <= 0 ||
                subtractHp <= 0) {
            return;
        }

        GameMsgProtocol.UserSubtractHpResult.Builder resultBuilder = GameMsgProtocol.UserSubtractHpResult.newBuilder();
        resultBuilder.setTargetUserId(targetUserId);
        resultBuilder.setSubtractHp(subtractHp);

        GameMsgProtocol.UserSubtractHpResult newResult = resultBuilder.build();
        GameMsgHandler.CHANNELS.writeAndFlush(newResult);
    }

    /**
     * 广播死亡消息
     *
     * @param targetUserId 被攻击者 Id
     */
    static private void broadcastDie(int targetUserId) {
        if (targetUserId <= 0) {
            return;
        }

        GameMsgProtocol.UserDieResult.Builder resultBuilder = GameMsgProtocol.UserDieResult.newBuilder();
        resultBuilder.setTargetUserId(targetUserId);

        GameMsgProtocol.UserDieResult newResult = resultBuilder.build();
        GameMsgHandler.CHANNELS.writeAndFlush(newResult);
    }
}
