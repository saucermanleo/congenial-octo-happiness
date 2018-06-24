package mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class QQTotalFriendsMapper extends Mapper <LongWritable,Text,Text,Text>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] names = line.split(" ");

        Text outkey = new Text(names[0]);
        for(int i = 1;i<names.length;i++){
            context.write(outkey,new Text(names[i]));
            System.out.println(names[i]);
        }
    }
}
