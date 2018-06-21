package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 执行job,需要在namenode上执行, ./hadoop jar hadoop-1.0-SNAPSHOT.jar mapreduce.RunWCJob(执行的类)
 */
public class RunWCJob {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.set("mapred,job.tracker","node1:9001");
        Job job = null;
        try {
            job = new Job(config,"word count");
            job.setJobName("word count");
            job.setJarByClass(RunWCJob.class);
            job.setMapperClass(WordCountMapper.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setReducerClass(WordCountReduce.class);

            job.setNumReduceTasks(2);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            FileInputFormat.setInputPaths(job, new Path("/input/test.txt"));
            FileOutputFormat.setOutputPath(job, new Path("/output/"));
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
