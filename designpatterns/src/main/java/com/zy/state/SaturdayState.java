package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 14:28
 */
public class SaturdayState extends AbstractSimpleState implements State{

    public SaturdayState() {
        super(new SundayState());
    }

    @Override
    public void action() {
        System.out.println("星期六了");
    }
}
