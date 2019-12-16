package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 14:28
 */
public class SundayState extends AbstractSimpleState implements State{

    public SundayState() {
        super(null);
    }

    @Override
    public void action() {
        System.out.println("星期天了");
    }
}
