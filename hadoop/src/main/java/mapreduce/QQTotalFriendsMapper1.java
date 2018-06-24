package mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class QQTotalFriendsMapper1 extends Mapper <LongWritable,Text,Text,Text>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] names = line.split(",");
        Text outkey = new Text(names[0]);
        Text outvalue = new Text(names[1].trim());
        context.write(outkey,outvalue);
    }
}
