package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:27
 */
public class WedState implements State {
    @Override
    public void doAction(Content content) {
        if(content.getWeather().equals("wed")){
            System.out.println("wed:过了一半了");
        }
    }
}
