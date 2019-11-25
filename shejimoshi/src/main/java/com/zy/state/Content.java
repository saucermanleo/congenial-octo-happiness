package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 17:06
 */
public class Content {
    private State state;
    private String weather;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Content(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void feel() {
        state.doAction(this);
    }
}
