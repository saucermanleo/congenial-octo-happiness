package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:27
 */
public class WedChainState extends AbstractChainState {


    public WedChainState() {
        super("wed", new ThursdayState());
    }

    @Override
    public void action() {
        System.out.println("wed:过了一半了");
    }
}
