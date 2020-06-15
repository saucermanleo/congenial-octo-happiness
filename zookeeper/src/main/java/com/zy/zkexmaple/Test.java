package com.zy.zkexmaple;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zk是基于seesion的  在断连后一定时间可以连接到其他服务器  基于watch可以分布式配置
 * @author : 生态环境-张阳
 * @date : 2020/6/3 0003 9:26
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ZooKeeper zk = new ZooKeeper("192.168.25.128:2181,192.168.25.129:2181,192.168.25.130:2181,192.168.25.131:2181", 3000, new Watcher() {
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                String path = event.getPath();
                Event.EventType type = event.getType();
                System.out.println(state);

                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("connected");
                        countDownLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                    default:
                }

                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                    case DataWatchRemoved:
                        break;
                    case ChildWatchRemoved:
                        break;
                    default:
                }
            }
        });
        countDownLatch.await();
        ZooKeeper.States states = zk.getState();
        switch (states) {
            case CONNECTING:
                System.out.println("CONNECTING");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("CONNECTED");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
            default:
        }
        zk.create("/ooxx","hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        final Stat state = new Stat();
        byte[] data = zk.getData("/ooxx", new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event);
                try {
                    zk.getData("/ooxx",this,state);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, state);

        //version 1.-1 为可以都可以设置 2.通过version可以防止别人提交冲突(cas)
        Stat stat = zk.setData("/ooxx", "newdata".getBytes(), 0);
        Stat stat1 = zk.setData("/ooxx", "newdata".getBytes(), stat.getAversion());

    }
}
