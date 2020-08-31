package org.zy.tinygame.handle;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:16
 */
public interface Handle<T extends GeneratedMessageV3> {


    void handle(ChannelHandlerContext ctx, Object msg);

    default T transfer(Object t){
        return (T)t;
    }
}
