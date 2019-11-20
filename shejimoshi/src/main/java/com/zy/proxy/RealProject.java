package com.zy.proxy;

public class RealProject implements Project {
    @Override
    public void desc() {
        System.out.println("real project");
    }
}
