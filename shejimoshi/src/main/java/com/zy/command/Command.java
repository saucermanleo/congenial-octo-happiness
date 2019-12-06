package com.zy.command;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 10:44
 */
public interface Command {
    void execute();

    void undo();
}
