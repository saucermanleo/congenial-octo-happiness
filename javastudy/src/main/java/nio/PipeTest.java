package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PipeTest {
    /**
     * 阻塞式通道 pipe用于线程间的信息传递
     */
    /*@Test
    public void test() {
        try {
            Pipe pipe = Pipe.open();
            CountDownLatch countDownLatch = new CountDownLatch(2);
            try (Pipe.SourceChannel sourceChannel = pipe.source();
                 Pipe.SinkChannel sinkChannel = pipe.sink()
            ) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ByteBuffer bb = ByteBuffer.allocate(1024);
                        try {
                            for (int i = 0; i < 10; i++) {
                                sourceChannel.read(bb);
                                bb.flip();
                                byte[] bytes = new byte[bb.limit()];
                                bb.get(bytes, 0, bb.limit());
                                System.out.println(new String(bytes, 0, bytes.length));
                                bb.clear();

                            }
                            countDownLatch.countDown();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ByteBuffer bb = ByteBuffer.allocate(1024);
                        for (int i = 0; i < 10; i++) {
                            bb.put((i + "").getBytes());
                            bb.flip();
                            try {
                                TimeUnit.SECONDS.sleep(2);
                                sinkChannel.write(bb);
                                bb.clear();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        countDownLatch.countDown();
                    }
                }).start();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
    @Test
    public void test1() throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Pipe pipe = Pipe.open();
        Pipe.SourceChannel sourceChannel = pipe.source();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        sourceChannel.configureBlocking(false);
        sinkChannel.configureBlocking(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Selector s = Selector.open();
                    sourceChannel.register(s, SelectionKey.OP_READ);
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    while (s.select() > 0) {
                        Iterator<SelectionKey> it = s.selectedKeys().iterator();
                        while (it.hasNext()) {
                           /* it.next();
                            it.remove();*/
                            while(sourceChannel.read(bb)!=0) {
                                bb.flip();
                                byte[] bytes = new byte[bb.limit()];
                                bb.get(bytes, 0, bb.limit());
                                System.out.println(new String(bytes, 0, bytes.length));
                                bb.clear();
                            }
                        }
                    }
                    countDownLatch.countDown();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteBuffer bb = ByteBuffer.allocate(1024);
                for (int i = 0; i < 10; i++) {
                    bb.put((i + "").getBytes());
                    bb.flip();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        sinkChannel.write(bb);
                        bb.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
