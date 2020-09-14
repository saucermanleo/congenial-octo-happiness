package com.bjmashibing.system.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/11 0011 10:30
 */
public class Test    {
    public static void main(String[] args) {
        System.out.println(512>>>4);
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
        byteBuf.release();
        ByteBuf byteBuf1 = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
        byteBuf1.release();
//        ByteBuf byteBuf1 = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
//        ByteBuf byteBuf2 = PooledByteBufAllocator.DEFAULT.directBuffer(8 * 1024);
//        ByteBuf byteBuf3 = PooledByteBufAllocator.DEFAULT.directBuffer(16 * 1024);
//        ByteBuf byteBuf24= PooledByteBufAllocator.DEFAULT.directBuffer(16 * 1024);
    }
}
