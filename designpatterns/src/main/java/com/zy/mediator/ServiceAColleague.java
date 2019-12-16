package com.zy.mediator;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/16 0016 14:56
 */
public class ServiceAColleague extends AbstractColleague {

    public ServiceAColleague(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void operate() {
        System.out.println("ServiceA do something");
    }


}
