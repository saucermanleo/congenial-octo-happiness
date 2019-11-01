package com.zy.subjectobserver;

public class WorkerBObserver implements Observer {
    @Override
    public void update(Subject subject) {
        BossSubject bossSubject =  (BossSubject)subject;
        if(bossSubject.isSate()){
            System.out.println("WorkerB works");
        }else{
            System.out.println("WorkerB palys game");
        }
    }
}
