package com.zy.zkexmaple;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import sun.misc.Unsafe;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

import static org.apache.zookeeper.Watcher.Event.KeeperState.SyncConnected;

/**
 * @author : 生态环境-张阳
 * @date : 2020/6/3 0003 13:34
 */
public class Config {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ZooKeeper zk = new ZooKeeper("192.168.25.128:2181,192.168.25.129:2181,192.168.25.130:2181,192.168.25.131:2181", 3000, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState() == SyncConnected) {
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        Stat stat = new Stat();
        stat = zk.exists("/conf",new WatcherConf(zk));
        if(stat!=null) {
            byte[] data = zk.getData("/conf", false, stat);
            System.out.println(new String(data));
        }else{
            System.out.println("节点不存在");
        }
        LockSupport.park();
    }
}
