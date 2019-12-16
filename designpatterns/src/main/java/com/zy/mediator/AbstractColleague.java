package com.zy.mediator;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/16 0016 14:50
 */
public abstract class AbstractColleague implements Collegeague{
    private Mediator mediator;

    public AbstractColleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void doSomething(){
        operate();
        getMediator().changed(this);
    }

    public abstract void operate();


}
