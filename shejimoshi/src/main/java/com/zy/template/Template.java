package com.zy.template;

public class Template extends AbstractTemplate {
    @Override
    protected void doSomthing() {
        System.out.println("做一些事");
    }

    @Override
    protected void doOther() {
        System.out.println("做另外一些事");
    }
}
