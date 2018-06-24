package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class QQTotalFriendsJob1 {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.set("mapred,job.tracker","node1:9001");
        Job job = null;
        try {
            job = new Job(config,"word count");
            job.setJarByClass(QQTotalFriendsJob1.class);
            job.setMapperClass(QQTotalFriendsMapper.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setReducerClass(QQTotalFriendsReducer.class);

            //job.setNumReduceTasks(2);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.setInputPaths(job, new Path("/input/qqfriends.txt"));
            FileOutputFormat.setOutputPath(job, new Path("/output/qq/"));
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
