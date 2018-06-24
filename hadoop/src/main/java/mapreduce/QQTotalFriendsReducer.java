package mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QQTotalFriendsReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        /*System.out.println(values);
        for(Iterator t = values.iterator(); t.hasNext();){
            String name = t.next().toString();
            System.out.println(name);
            for(Iterator text = values.iterator(); text.hasNext();){
                String other = text.next().toString();
                System.out.println(other);
                if(!name.equals(other)){
                    Text out1 = new Text(name+":"+other+","+key);
                    Text out2 = new Text(other+":"+name+","+key);
                    context.write(out1,null);
                    context.write(out2,null);
                }
            }
        }*/
        System.out.println("reduce start");
        ArrayList<String> list1 = new ArrayList<String>();
        for (Text t1 : values) {
            System.out.println(t1);
            list1.add(t1.toString());
        }

        List<String> list2 = new ArrayList<String>(list1);
        for (String t : list1) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.println(t);
        }

        for (int i = 0; i < list1.size(); i++) {
            String name = list1.get(i);
            System.out.println(name);
            for (int j = i+1; j < list2.size(); j++) {
                String other = list2.get(j);
                System.out.println(other);
                //if (!name.equals(other)) {
                    Text out1 = new Text(name + ":" + other + "," + key);
                    Text out2 = new Text(other + ":" + name + "," + key);
                    context.write(out1, null);
                    context.write(out2, null);
                //}
            }
        }
    }
}

