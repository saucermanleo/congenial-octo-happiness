package com.zy.ChainOfResponsibility;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 15:12
 */
public class TueHandler   extends AbstractHandler implements Handler {

    public TueHandler() {
        super("tue");
    }

    @Override
    public void doHandle() {
        System.out.println("星期二");
    }
}
