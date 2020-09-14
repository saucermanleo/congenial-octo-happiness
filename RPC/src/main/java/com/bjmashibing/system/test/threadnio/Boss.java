package com.bjmashibing.system.test.threadnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/23 0023 14:57
 */
public class Boss {
    private int num;
    private WorkerRunnable[] workers;
    private BossRunnable[] bosss;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private int round;
    private int n;

    public int getNum() {
        return num;
    }

    public WorkerRunnable[] getWorkers() {
        return workers;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public Boss(int num, int n) {
        this.num = num;
        workers = new WorkerRunnable[num];
        this.n = n;
        bosss = new BossRunnable[n];
        for (int i = 0; i < workers.length; i++) {
            WorkerRunnable workerRunnable = new WorkerRunnable();
            workers[i] = workerRunnable;
            new Thread(workerRunnable).start();
        }

        for (int i = 0; i < bosss.length; i++) {
            BossRunnable bossRunnable = new BossRunnable(this);
            bosss[i] = bossRunnable;
            new Thread(bossRunnable).start();
        }
    }

    public void bind(int port){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("172.16.121.179",port));
            int turn = nextBoss();
            BlockingQueue<Channel> channels = bosss[turn].blockingQueue;
            channels.add(serverSocketChannel);
            bosss[turn].getSelector().wakeup();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private int nextBoss(){
        return round++%n;
    }
}
