package com.zy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel fileChannel = fileInputStream.getChannel();
        FileChannel fileChannel1 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while(true){
            int read = fileChannel.read(byteBuffer);
            if(read ==-1)
            break;
            byteBuffer.flip();
            fileChannel1.write(byteBuffer);
            byteBuffer.clear();
        }
    }
}
