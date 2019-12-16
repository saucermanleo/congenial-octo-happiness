package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:30
 */
public class StateTest {
    public static void main(String[] args) {
        //有if判断的
        Content content = new Content(new MonChainState());
        content.setWeather("mon");
        content.feel();
        //普通的状态转换
        State state = new ThursdayState();
        content.setState(state);
        content.feel();
        content.feel();
        content.feel();
        content.feel();
        content.feel();
    }
}
