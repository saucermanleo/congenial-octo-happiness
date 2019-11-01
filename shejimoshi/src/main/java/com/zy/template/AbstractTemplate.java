package com.zy.template;

public abstract class AbstractTemplate {

    public void templateMethod(){
        doSomthing();
        hookMethod();
        doOther();
    }

    private void hookMethod(){
        System.out.println("固定逻辑+");
    }

    protected abstract void doSomthing();

    protected abstract void doOther();
}
