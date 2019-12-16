package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 10:54
 */
public abstract class AbstractChainState implements State {
    private String state;
    private State nextState;

    public AbstractChainState(String date, State nextState) {
        this.state = date;
        this.nextState = nextState;
    }

    @Override
    public void doAction(Content content){
        if(state.equals(content.getWeather())){
            this.action();
        }else{
            if(nextState!=null) {
                content.setState(nextState);
                content.feel();
            }
        }
    }

    public abstract void action();

}
