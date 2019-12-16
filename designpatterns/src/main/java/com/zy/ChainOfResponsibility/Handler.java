package com.zy.ChainOfResponsibility;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 14:59
 */
public interface Handler {
    void handle(String condition);
    Handler setHandler(Handler handler);
}
