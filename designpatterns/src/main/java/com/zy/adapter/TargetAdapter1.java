package com.zy.adapter;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 17:20
 */
public class TargetAdapter1 implements Target {

    private Adaptee adaptee;

    public TargetAdapter1(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
