package org.zy.tinygame.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.zy.tinygame.GameMsgProtocol;
import org.zy.tinygame.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/28 0028 11:29
 */
public class GameMsgHandler extends ChannelInboundHandlerAdapter {


    public static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static final ConcurrentHashMap<Integer,User> users = new ConcurrentHashMap<Integer, User>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNELS.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        CHANNELS.remove(ctx.channel());

        // 先拿到用户 Id
        Integer userId = (Integer)ctx.channel().attr(AttributeKey.valueOf("userId")).get();

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

        if(msg instanceof GameMsgProtocol.UserEntryCmd){
            GameMsgProtocol.UserEntryCmd userEntryCmd =(GameMsgProtocol.UserEntryCmd) msg;

            GameMsgProtocol.UserEntryResult.Builder builder = GameMsgProtocol.UserEntryResult.newBuilder();
            builder.setUserId(userEntryCmd.getUserId()  );
            builder.setHeroAvatar(userEntryCmd.getHeroAvatar());

            User user = new User();
            user.setUserId(userEntryCmd.getUserId());
            user.setRole(userEntryCmd.getHeroAvatar());
            users.put(user.getUserId(),user);

            ctx.channel().attr(AttributeKey.valueOf("userId")).set(user.getUserId());

            CHANNELS.writeAndFlush(builder.build());

        }else if(msg instanceof GameMsgProtocol.WhoElseIsHereCmd){
            GameMsgProtocol.WhoElseIsHereCmd object =(GameMsgProtocol.WhoElseIsHereCmd) msg;
            GameMsgProtocol.WhoElseIsHereResult.Builder builder =  GameMsgProtocol.WhoElseIsHereResult.newBuilder();
            for (User value : users.values()) {
                builder.addUserInfo(GameMsgProtocol.WhoElseIsHereResult.UserInfo.newBuilder().setUserId(value.getUserId()).setHeroAvatar(value.getRole()).build());
            }
            ctx.channel().writeAndFlush(builder.build());
        }else if(msg instanceof GameMsgProtocol.UserMoveToCmd){

            Integer userId = (Integer)ctx.channel().attr(AttributeKey.valueOf("userId")).get();
            if(userId == null) {
                return;
            }

            GameMsgProtocol.UserMoveToCmd object =(GameMsgProtocol.UserMoveToCmd) msg;
            GameMsgProtocol.UserMoveToResult.Builder builder =  GameMsgProtocol.UserMoveToResult.newBuilder();
            builder.setMoveToPosX(object.getMoveToPosX()).setMoveToPosY(object.getMoveToPosY()).setMoveUserId(userId);
            CHANNELS.writeAndFlush(builder.build());
        }




    }
}
