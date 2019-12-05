package com.zy.ChainOfResponsibility;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 15:14
 */
public class WedHandler   extends AbstractHandler implements Handler {

    public WedHandler() {
        super("wed");
    }

    @Override
    public void doHandle() {
        System.out.println("星期三");
    }
}
