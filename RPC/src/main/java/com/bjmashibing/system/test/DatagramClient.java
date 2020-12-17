package com.bjmashibing.system.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author : 生态环境-张阳
 * @date : 2020/12/17 0017 10:13
 */
public class DatagramClient  {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String mes = "datagrammeseage";
        buffer.clear();
        buffer.put(mes.getBytes("UTF-8"));
        buffer.flip();
        System.out.println("send msg:" + mes);
        int send = channel.send(buffer, new InetSocketAddress("localhost",1234));

        byte[] bytes = mes.getBytes("UTF-8");
        DatagramSocket datagramSocket = new DatagramSocket();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,new InetSocketAddress("localhost",1235));
        datagramSocket.send(datagramPacket);
    }
}
