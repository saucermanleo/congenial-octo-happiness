package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:24
 */
public class TueChainState extends AbstractChainState {


    public TueChainState() {
        super("tue", new WedChainState());
    }

    @Override
    public void action() {
        System.out.println("星期二:上班中");
    }
}
