package com.zy.mediator;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/16 0016 14:58
 */
public class ServiceBcolleague extends AbstractColleague {

    public ServiceBcolleague(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void operate() {
        System.out.println("ServiceB do something");
    }
}
