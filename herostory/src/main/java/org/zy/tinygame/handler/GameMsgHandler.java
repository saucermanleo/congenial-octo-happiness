package org.zy.tinygame.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.User;
import org.zy.tinygame.handle.Handle;
import org.zy.tinygame.handle.HandleFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/28 0028 11:29
 */
public class GameMsgHandler extends ChannelInboundHandlerAdapter {


    public static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();
    public static final ConcurrentHashMap<Integer, Executor> executors = new ConcurrentHashMap<Integer, Executor>();
    public static final Executor executor = Executors.newSingleThreadExecutor();
    public static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNELS.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        CHANNELS.remove(ctx.channel());

        // 先拿到用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

        if (null == userId) {
            return;
        }

        users.remove(userId);

        GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
        resultBuilder.setQuitUserId(userId);

        GameMsgProtocol.UserQuitResult newResult = resultBuilder.build();
        CHANNELS.writeAndFlush(newResult);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        Handle<?> handle = HandleFactory.getHandle(msg);
        if (handle != null) {
            handle.handle(ctx, msg);
        }

    }
}
