import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * ZooKeeper分布式锁的实现(不支持多线程),DistributedLock构造方法的两个参数决定了一个同步方法.
 */
public class DistributedLock implements Lock, Watcher {

    private CountDownLatch zklatch = new CountDownLatch(1);
    private CountDownLatch cdl = new CountDownLatch(1);
    private String concurrentNode;
    private ZooKeeper zk = null;
    private int sessionTimeout = 30000;
    private String root;
    private String nodeName;

    public DistributedLock(String config, String lockName) {
        root = "/" + lockName;
        nodeName = root + "/" + lockName + "_lock_";
        try {
            zk = new ZooKeeper(config, sessionTimeout, this);
            zklatch.await();
            Stat stat = zk.exists(root, false);
            if (stat == null) {
                zk.create(root, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        try {
            if (tryLock()) {
                System.out.println("获得锁");
                return;
            } else {
                cdl.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        try {
            concurrentNode = zk.create(nodeName, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(concurrentNode);
            List<String> list = zk.getChildren(root, false);
            list = list.stream().sorted().collect(Collectors.toList());
            if ((root + "/" + list.get(0)).equals(concurrentNode)) {
                return true;
            } else {
                String node = concurrentNode.substring(concurrentNode.lastIndexOf('/') + 1);
                String preNode = list.get(Collections.binarySearch(list, node) - 1);
                if (zk.exists(root + "/" + preNode, true) == null) {
                    cdl.countDown();
                }

            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        System.out.println("释放锁");
        try {
            zk.delete(concurrentNode, -1);
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            zklatch.countDown();
        }
        if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("唤醒线程");
            cdl.countDown();
        }
    }
}
