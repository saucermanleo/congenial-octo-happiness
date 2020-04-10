package com.zy.visitor;

/**
 * 打印出对象的类型功能
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:36
 */
public class TypeVisitor implements Visitor{
    @Override
    public void visit(String s) {
        System.out.println("String");
    }

    @Override
    public void visit(Integer i) {
        System.out.println("Integer");
    }

    @Override
    public void visit(Float f) {
        System.out.println("Float");
    }
}
