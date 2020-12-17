package com.bjmashibing.system.test;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author : 生态环境-张阳
 * @date : 2020/12/17 0017 10:01
 */
public class DatagramServer {

    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(1234));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte b[];
        while(true){
            SocketAddress receive = datagramChannel.receive(buffer);
            if(receive!=null){
                buffer.flip();
                System.out.println("receive remote " +  receive.toString() + ":"  + new String(buffer.array(), "UTF-8"));
                break;
            }
        }

        DatagramSocket datagramSocket = new DatagramSocket(1235);
        byte[] inBuff = new byte[1024];
        DatagramPacket inPacket = new DatagramPacket(inBuff , inBuff.length);
        datagramSocket.receive(inPacket);
        b = new byte[inPacket.getLength()];
        for(int i=0; i<inPacket.getLength(); ++i) {
            b[i] = inBuff[i];
        }
        InetAddress address = inPacket.getAddress();
        System.out.println("receive remote " +  address.toString() + ":"  + new String(b, "UTF-8"));
    }


}
