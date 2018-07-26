import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TestDistributedLock {
    public static void main(String[] args) {
        DistributedLock lock = new DistributedLock("127.0.0.1:2181", "test");
        lock.lock();
        Stream.iterate(0,x->x+1).limit(10).forEach(x->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        lock.unlock();
    }
}
