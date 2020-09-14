package com.bjmashibing.system.test.threadnio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/23 0023 16:12
 */
public class BossRunnable extends AbstractRunnable {

    private Boss boss;
    private int num;
    private WorkerRunnable[] workers;
    private AtomicInteger atomicInteger;

    public BossRunnable(Boss boss) {
        super();
        this.boss = boss;
        this.num = boss.getNum();
        this.atomicInteger = boss.getAtomicInteger();
        this.workers = boss.getWorkers();
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
                        if (key.isAcceptable()) {
                            accept(key);
                        }
                    }
                }

                while (!blockingQueue.isEmpty()) {
                    ServerSocketChannel take = (ServerSocketChannel) blockingQueue.take();
                    take.configureBlocking(false);
                    take.register(selector, SelectionKey.OP_ACCEPT);
                    System.out.println(Thread.currentThread().getName() + ": " + take.socket().getLocalPort());
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void accept(SelectionKey key) throws IOException {
        int num = nextTurn();
        BlockingQueue<Channel> socketChannels = workers[num].blockingQueue;
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel accept = channel.accept();
        socketChannels.add(accept);
        workers[num].getSelector().wakeup();
    }

    private int nextTurn(){
        return atomicInteger.getAndIncrement()%num;
    }
}
