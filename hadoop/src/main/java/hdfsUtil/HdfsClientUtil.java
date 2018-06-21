package hdfsUtil;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClientUtil {

    private volatile static FileSystem filesystem = null;
    private FileSystem fs = newInstence();

    public static FileSystem newInstence() {
        if (filesystem == null) {
            synchronized (HdfsClientUtil.class) {
                if (filesystem == null) {
                    Configuration conf = new Configuration();
                    conf.set("fs.default.name", "hdfs://node1:9000");
                    try {
                        filesystem = FileSystem.get(new URI("hdfs://node1:9000"),conf,"root");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return filesystem;
    }

    public void mkdir(String path) {
        try {
            fs.mkdirs(new Path(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(String src ,String dst){
        try {
            fs.copyFromLocalFile(new Path(src),new Path(dst));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(String path ){
        try {
            fs.delete(new Path(path),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void removetest(){
        remove("/output/");
    }

    @Test
    public void uploadtest(){
        upload("D:/test.txt","/input/test.txt");
    }


    @Test
    public void mkdertest(  ){
        mkdir("/input/");
    }
}
