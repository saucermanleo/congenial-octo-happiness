package com.zy.state;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 14:17
 */
public abstract class AbstractSimpleState implements State {
    private State nextState;

    public AbstractSimpleState(State nextState) {
        this.nextState = nextState;
    }

    @Override
    public void doAction(Content content) {
        this.action();
        if (nextState != null) {
            content.setState(nextState);
        }
    }

    public abstract void action();
}
