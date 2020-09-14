package com.bjmashibing.system.test.threadnio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.Selector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/23 0023 15:59
 */
public abstract class AbstractRunnable extends ThreadLocal<BlockingQueue<Channel>> implements Runnable {
    public Selector getSelector() {
        return selector;
    }


    protected Selector selector;
    protected BlockingQueue<Channel> blockingQueue;

    public AbstractRunnable() {
        init();
    }

    @Override
    protected BlockingQueue<Channel> initialValue() {
        return new LinkedBlockingQueue<Channel>();
    }


    public void init() {
        try {
            selector = Selector.open();
            blockingQueue = new LinkedBlockingQueue<>();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
