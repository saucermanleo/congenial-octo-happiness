package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:24
 */
public class TueState implements State {
    @Override
    public void doAction(Content content) {
        if(content.getWeather().equals("tue")){
            System.out.println("星期二:上班中");
        }else{
            content.setState(new WedState());
            content.feel();
        }
    }
}
