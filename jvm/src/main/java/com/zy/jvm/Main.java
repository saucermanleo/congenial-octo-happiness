package com.zy.jvm;

public class Main {

    private Object instance;


    public Main() {
        byte[] bytes = new byte[20*1024*1024];
    }
   //-verbose:gc -XX:+PrintGCDetails
    public static void main(String[] args) {
        Main m1 = new Main();
        Main m2 = new Main();
        m1.instance = m2;
        m2.instance = m1;
        m1 =null;
        m2 =null;
        System.gc();
    }
}
