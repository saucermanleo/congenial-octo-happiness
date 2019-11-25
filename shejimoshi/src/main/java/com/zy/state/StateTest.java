package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:30
 */
public class StateTest {
    public static void main(String[] args) {
        Content content = new Content(new MonState());
        content.setWeather("wed");
        content.feel();
    }
}
