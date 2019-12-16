package com.zy.ChainOfResponsibility;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 15:00
 */
public class MonHandler extends AbstractHandler implements Handler {

    public MonHandler() {
        super("mon");
    }

    @Override
    public void doHandle() {
        System.out.println("星期一");
    }
}
