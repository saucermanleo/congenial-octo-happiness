package org.zy.tinygame.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.MoveState;
import org.zy.tinygame.User;
import org.zy.tinygame.UserEntity;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/10 0010 11:17
 */
public class UserLoginCmdHandler implements Handle<GameMsgProtocol.UserLoginCmd> {
    @Override
    public void handle(ChannelHandlerContext ctx, Object msg) {
        if (null == ctx ||
                null == msg) {
            return;
        }



        GameMsgProtocol.UserLoginCmd cmd = this.transfer(msg);
        String name = cmd.getUserName();

        Promise<UserEntity> promise = new DefaultPromise<>(ctx.executor());
        promise.addListener(x -> {
            System.out.println(Thread.currentThread());
            Object now = x.getNow();
            UserEntity userEntity = (UserEntity) now;
            // 新建用户,
            User newUser = new User();
            newUser.setUserId(GameMsgHandler.ATOMIC_INTEGER.getAndIncrement());
            newUser.setRole(userEntity.heroAvatar);
            newUser.name = userEntity.userName;
            newUser.blood = 100;
            newUser.moveState = new MoveState();
            // 并将用户加入管理器
            GameMsgHandler.users.put(newUser.getUserId(), newUser);
            GameMsgHandler.executors.put(newUser.getUserId(),ctx.executor());
            // 将用户 Id 附着到 Channel
            ctx.channel().attr(AttributeKey.valueOf("userId")).set(newUser.getUserId());

            // 登陆结果构建者
            GameMsgProtocol.UserLoginResult.Builder
                    resultBuilder = GameMsgProtocol.UserLoginResult.newBuilder();
            resultBuilder.setUserId(newUser.getUserId());
            resultBuilder.setUserName(newUser.name);
            resultBuilder.setHeroAvatar(newUser.getRole());

            // 构建结果并发送
            GameMsgProtocol.UserLoginResult newResult = resultBuilder.build();
            ctx.writeAndFlush(newResult);
        });

        new LoginService().login(name,promise);

    }
}
