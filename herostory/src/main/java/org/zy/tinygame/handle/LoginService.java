package org.zy.tinygame.handle;

import io.netty.util.concurrent.Promise;
import org.zy.tinygame.UserEntity;
import org.zy.tinygame.handler.GameMsgHandler;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/10 0010 14:02
 */
public class LoginService {
    public void login(String name ,Promise<UserEntity> promise) {
        GameMsgHandler.executor.execute(() -> {
            System.out.println(Thread.currentThread());
            UserEntity userEntity = null;
            userEntity = new UserEntity();
            userEntity.userName = name;
            userEntity.password = "a";
            userEntity.heroAvatar = "Hero_Shaman";
            promise.setSuccess(userEntity);
        });
    }
}
