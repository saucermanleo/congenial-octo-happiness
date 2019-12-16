package com.zy.mediator;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/16 0016 15:04
 */
public class MediatorImpl implements Mediator {

    private Collegeague a,b,c;

    public MediatorImpl() {
    }

    public MediatorImpl(Collegeague a, Collegeague b, Collegeague c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Collegeague getA() {
        return a;
    }

    public MediatorImpl setA(Collegeague a) {
        this.a = a;
        return this;
    }

    public Collegeague getB() {
        return b;
    }

    public MediatorImpl setB(Collegeague b) {
        this.b = b;
        return this;
    }

    public Collegeague getC() {
        return c;
    }

    public MediatorImpl setC(Collegeague c) {
        this.c = c;
        return this;
    }

    @Override
    public void changed(Collegeague colleague) {
        if(colleague instanceof ServiceAColleague) {
            b.doSomething();
        }

        if(colleague instanceof  ServiceBcolleague){
            c.doSomething();
        }
    }
}
