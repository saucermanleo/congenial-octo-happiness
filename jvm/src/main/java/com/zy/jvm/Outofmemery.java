package com.zy.jvm;

import java.util.ArrayList;
import java.util.List;

public class Outofmemery {
    /*-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError*/
    public static void main(String[] args) {
        List<Demo> demoList = new ArrayList<Demo>();
        while(true){
            demoList.add(new Demo());
        }
    }


}

class Demo {

}