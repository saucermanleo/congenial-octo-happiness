package com.bjmashibing.system.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/11 0011 10:30
 */
public class Test    {
    List<String> s = new LinkedList<>();
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(512>>>4);
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
        byteBuf.release();
        ByteBuf byteBuf1 = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
        byteBuf1.release();
//        ByteBuf byteBuf1 = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
//        ByteBuf byteBuf2 = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
//        ByteBuf byteBuf3 = PooledByteBufAllocator.DEFAULT.directBuffer(16 * 1024);
//        ByteBuf byteBuf24= PooledByteBufAllocator.DEFAULT.directBuffer(16 * 1024);

        Type s = Test.class.getDeclaredField("s").getGenericType();
        System.out.println(s);
        if(s instanceof ParameterizedType){
            s = (ParameterizedType) s;
            System.out.println(((ParameterizedType) s).getActualTypeArguments()[0]);
        }


    }
}
