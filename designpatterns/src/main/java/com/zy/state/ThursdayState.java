package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 14:21
 */
public class ThursdayState extends AbstractSimpleState implements State {

    public ThursdayState() {
        super(new FridayState());
    }

    @Override
    public void action() {
        System.out.println("星期四了");
    }
}
