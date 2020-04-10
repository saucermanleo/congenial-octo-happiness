package com.zy.visitor;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:37
 */
public interface Element {
    void accept(Visitor visitor);
}
