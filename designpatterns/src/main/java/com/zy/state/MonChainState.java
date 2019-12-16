package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:17
 */
public class MonChainState extends AbstractChainState {

    public MonChainState() {
        super("mon",new TueChainState());
    }

    @Override
    public void action() {
        System.out.println("星期一:上班了,不开心");
    }
}
