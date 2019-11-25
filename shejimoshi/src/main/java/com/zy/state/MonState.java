package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:17
 */
public class MonState implements State {
    @Override
    public void doAction(Content content) {
        if(content.getWeather().equals("mon")){
            System.out.println("星期一:上班了,不开心");
        }else{
            content.setState(new TueState());
            content.feel();
        }
    }
}
