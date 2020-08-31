package org.zy.tinygame.handle;

import org.zy.tinygame.GameMsgProtocol;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:35
 */
public class HandleFactory {
    private HandleFactory() {
    }

    public static Handle<?> getHandle(Object msg) {
        if (msg instanceof GameMsgProtocol.UserEntryCmd) {
            return new UserEntryCmdHandle();
        } else if (msg instanceof GameMsgProtocol.WhoElseIsHereCmd) {
            return new WhoElseIsHereCmdHandle();
        } else if (msg instanceof GameMsgProtocol.UserMoveToCmd) {
            return new UserMoveToCmdHandle();
        }
        return null;
    }
}
