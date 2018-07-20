package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

public class TestDatagramSend {
    public static void main(String[] args) {
        try(DatagramChannel dc = DatagramChannel.open()){
            dc.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String s = scanner.next();
                buffer.put((new Date().toString()+"\n"+s).getBytes());
                buffer.flip();
                dc.send(buffer,new InetSocketAddress("127.0.0.1",9000));
                buffer.clear();
            }

        }catch (IOException exception){
            System.out.println(exception.getStackTrace());
        }

    }
}
