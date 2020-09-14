package com.bjmashibing.system.test.threadnio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/23 0023 14:18
 */
public class WorkerRunnable extends AbstractRunnable {


    public WorkerRunnable() {
        super();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int num = selector.select();

                if (num > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            handler(key);
                        }
                    }
                }
                while (!blockingQueue.isEmpty()) {
                    SocketChannel take = (SocketChannel) blockingQueue.take();
                    take.configureBlocking(false);
                    take.register(selector, SelectionKey.OP_READ);
                    System.out.println(Thread.currentThread().getName() + ": " + take.socket().getPort());
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
