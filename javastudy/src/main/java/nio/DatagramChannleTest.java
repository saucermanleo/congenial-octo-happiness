package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * UDP(传输层)实现 非阻塞模式
 */
public class DatagramChannleTest {

    @Test
    public void receive(){
        try (DatagramChannel channel = DatagramChannel.open()){
            channel.configureBlocking(false);
            Selector selector = Selector.open();
            channel.bind(new InetSocketAddress(9000));
            channel.register(selector,SelectionKey.OP_READ);
            while(selector.select()>0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey sk =  it.next();
                    if(sk.isReadable()){
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.receive(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0,buffer.limit()));
                        buffer.clear();
                    }
                    it.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
