package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 14:24
 */
public class FridayState extends AbstractSimpleState implements State{

    public FridayState() {
        super( new SaturdayState());
    }

    @Override
    public void action() {
        System.out.println("星期五了");
    }
}
