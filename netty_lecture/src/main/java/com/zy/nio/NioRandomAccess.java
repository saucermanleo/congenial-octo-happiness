package com.zy.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioRandomAccess {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("input.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        //文件映射到内存,内存改变 文件自动改变
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');

        randomAccessFile.close();

    }
}