package com.zy.pmk.pojo;

public class D{
    public class A{};//位于类中，有enclosed class属性
    public D(){
        class B{};//类位于构造器中，有enclosed constructor属性
    }
    public static void main(String[] args) throws Exception
    {
        Class c1 = new InterfaceA(){}.getClass();
        //类位于方法中，有enclosed method属性
    }
}