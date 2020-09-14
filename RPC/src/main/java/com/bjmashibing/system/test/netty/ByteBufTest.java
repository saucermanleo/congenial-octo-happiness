package com.bjmashibing.system.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/25 0025 15:43
 */
public class    ByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(8,20);
        byteBuf.release();
        UnpooledByteBufAllocator.DEFAULT.directBuffer(8,20);
        PooledByteBufAllocator.DEFAULT.heapBuffer();
        pirnt(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        pirnt(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        pirnt(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        pirnt(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        pirnt(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        pirnt(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        pirnt(byteBuf);



    }

    public static void pirnt(ByteBuf byteBuf){
        System.out.println("----------------------");
        System.out.println("isWritable:"+byteBuf.isWritable());
        System.out.println("writerIndex:"+byteBuf.writerIndex());
        System.out.println("writableBytes:"+byteBuf.writableBytes());
        System.out.println("isReadable:"+byteBuf.isReadable());
        System.out.println("readerIndex:"+byteBuf.readerIndex());
        System.out.println("readableBytes:"+byteBuf.readableBytes());
        System.out.println("isDirect:"+byteBuf.isDirect());
        System.out.println("capacity:"+byteBuf.capacity());
        System.out.println("maxCapacity:"+byteBuf.maxCapacity());
    }
}
