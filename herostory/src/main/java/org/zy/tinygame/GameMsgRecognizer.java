package org.zy.tinygame;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 15:45
 */
public class GameMsgRecognizer {

    private static final Map<Integer, Message.Builder> BuilderMap = new HashMap<>();
    private static final Map<Class<? extends GeneratedMessageV3>, Integer> MsgCodeMap = new HashMap<>();


    static {
        for (Class<?> declaredClass : GameMsgProtocol.class.getDeclaredClasses()) {
            if (!GeneratedMessageV3.class.isAssignableFrom(declaredClass)) {
                continue;
            }
            String className = declaredClass.getSimpleName().toLowerCase();
            for (GameMsgProtocol.MsgCode value : GameMsgProtocol.MsgCode.values()) {
                String name = value.name().replace("_", "").toLowerCase();

                if (name.startsWith(className)) {
                    try {
                        GeneratedMessageV3 getDefaultInstance = (GeneratedMessageV3) declaredClass.getMethod("getDefaultInstance").invoke(declaredClass);
                        BuilderMap.put(value.getNumber(), getDefaultInstance.toBuilder());

                        MsgCodeMap.put(getDefaultInstance.getClass(), value.getNumber());
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }


                } else {
                    continue;
                }
            }
        }
    }


    private GameMsgRecognizer() {
    }

    public static int getMsgCode(Object msg) {
        return MsgCodeMap.get(msg.getClass());
    }


    public static Message.Builder getBuilder(int msgCode) {
        return BuilderMap.get(msgCode);
    }

}
