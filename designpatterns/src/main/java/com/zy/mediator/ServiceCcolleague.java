package com.zy.mediator;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/16 0016 15:05
 */
public class ServiceCcolleague extends AbstractColleague {

    public ServiceCcolleague(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void operate() {
        System.out.println("Service c do something");
    }
}
