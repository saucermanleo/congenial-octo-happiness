package mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class QQTotalFriendsReducer1 extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (Text t : values) {
            sb.append(t.toString() + " ");
        }
        Text out = new Text(sb.toString());
        context.write(key, out);
    }
}
