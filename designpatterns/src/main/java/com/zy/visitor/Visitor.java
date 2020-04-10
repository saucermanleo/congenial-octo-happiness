package com.zy.visitor;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:33
 */
public interface Visitor {
    void visit(String s);

    void visit(Integer i);

    void visit(Float f);
}
