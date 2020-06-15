package com.zy.zkexmaple;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author : 生态环境-张阳
 * @date : 2020/6/4 0004 13:20
 */
public class WatcherConf implements Watcher {

    ZooKeeper zooKeeper;

    public WatcherConf(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                System.out.println("节点不存在");
                try {
                    zooKeeper.exists("/conf",this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case NodeCreated:
                hand();
                break;
            case NodeDeleted:
                System.out.println("节点被删除");
                try {
                    zooKeeper.exists("/conf",this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case NodeDataChanged:
                hand();
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

    private void hand(){
        try {
            System.out.println(new String(zooKeeper.getData("/conf",this,new Stat())));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
