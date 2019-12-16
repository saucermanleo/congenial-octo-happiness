package com.zy.mediator;

/**
 * 用一个中介者对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使耦合松散，而且可以独立地改变它们之间的交互。
 *
 * @author : 生态环境-张阳
 * @date : 2019/12/16 0016 15:11
 */
public class MediatorTest {
    public static void main(String[] args) {
        MediatorImpl mediator = new MediatorImpl();
        Collegeague a = new ServiceAColleague(mediator);
        mediator.setA(a)
                .setB(new ServiceBcolleague(mediator))
                .setC(new ServiceCcolleague(mediator));
        a.doSomething();
    }
}
