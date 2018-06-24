package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class QQTotalFriendsJob2 {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.set("mapred,job.tracker","node1:9001");
        Job job = null;
        try {
            job = new Job(config,"word count");
            job.setJarByClass(QQTotalFriendsJob2.class);
            job.setMapperClass(QQTotalFriendsMapper1.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setReducerClass(QQTotalFriendsReducer1.class);

            //job.setNumReduceTasks(2);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.setInputPaths(job, new Path("/output/qq/part-r-00000"));
            FileOutputFormat.setOutputPath(job, new Path("/output/qq1/"));
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
