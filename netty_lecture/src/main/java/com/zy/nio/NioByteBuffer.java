package com.zy.nio;

import java.nio.ByteBuffer;

public class NioByteBuffer {
    public static void main(String[] args) {
        //可以放入基本类型
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putInt(1).putLong(34).putChar('你').putFloat(3.3f);
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getFloat());

        //可以截取一段作为新的buffer  并且和原有buffer共享内存
        byteBuffer.clear();
        for(int i=0;i<10;i++){
            byteBuffer.put((byte)i);
        }
        byteBuffer.position(3);
        byteBuffer.limit(5);
        ByteBuffer slice = byteBuffer.slice();
        for(int i=0;i<slice.capacity();i++){
            byte b = slice.get(i);
            b *=2;
            slice.put(i,b);
        }
        byteBuffer.position(0);
        byteBuffer.limit(10);
        while(byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
        //可以转化为只读buffer
        ByteBuffer byteBufferR = byteBuffer.asReadOnlyBuffer();
        byteBufferR.put((byte)3);


    }
}
