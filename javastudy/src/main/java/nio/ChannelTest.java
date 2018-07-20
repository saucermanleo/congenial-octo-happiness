package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
/*
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 */
public class ChannelTest {
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        try (
                FileInputStream fileInputStream = new FileInputStream("E:\\迅雷下载\\HEYZO-0491\\HEYZO-0491.mp4");
                FileOutputStream fileOutputStream = new FileOutputStream("E:\\迅雷下载\\HEYZO-0491\\4.mp4");
                FileChannel inChannel = fileInputStream.getChannel();
                FileChannel outChannel = fileOutputStream.getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        try (
                FileChannel inchannel = FileChannel.open(Paths.get("E:\\迅雷下载\\HEYZO-0491\\HEYZO-0491.mp4"),StandardOpenOption.READ);
                FileChannel outchannel = FileChannel.open(Paths.get("E:\\迅雷下载\\HEYZO-0491\\2.mp4"),StandardOpenOption.WRITE , StandardOpenOption.CREATE)
                )
        {
            inchannel.transferTo(0,inchannel.size(),outchannel);
            //outchannel.transferFrom(inchannel,0,inchannel.size());
        }catch(IOException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 直接缓冲区
     */
    @Test
    public void test3() {
        long start = System.currentTimeMillis();
        try (
                FileChannel inchannel = FileChannel.open(Paths.get("E:\\迅雷下载\\HEYZO-0491\\HEYZO-0491.mp4"),StandardOpenOption.READ);
                FileChannel outchannel = FileChannel.open(Paths.get("E:\\迅雷下载\\HEYZO-0491\\3.mp4"),StandardOpenOption.WRITE , StandardOpenOption.READ,StandardOpenOption.CREATE)
        )
        {
            MappedByteBuffer inMappedBuf = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, inchannel.size());
            MappedByteBuffer outMappedBuf = outchannel.map(FileChannel.MapMode.READ_WRITE, 0, inchannel.size());
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        }catch(IOException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test4() throws IOException {
        FileChannel outchannel = FileChannel.open(Paths.get("1.txt"),StandardOpenOption.READ , StandardOpenOption.CREATE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (outchannel.read(buffer) != -1) {
            buffer.flip();
            CharBuffer cbuffer = Charset.forName("GBK").decode(buffer);
            System.out.println(cbuffer.toString());
            byte[] bytes = new byte[buffer.limit()];
            buffer.flip();
            buffer.get(bytes,0,buffer.limit());
            System.out.println(new String(bytes,0,bytes.length,Charset.forName("GBK")));
            System.out.println(Charset.defaultCharset());
            buffer.clear();
        }
    }

    //字符集
    @Test
    public void test6() throws IOException{
        Charset cs1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("尚硅谷威武！");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = ce.encode(cBuf);

        for (int i = 0; i < 12; i++) {
            System.out.println(bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("------------------------------------------------------");

        Charset cs2 = Charset.forName("utf-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }

    @Test
    public void test5(){
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
