package com.zy.subjectobserver;

public class WorkerAObserver implements Observer{

    @Override
    public void update(Subject subject) {
        BossSubject bossSubject =  (BossSubject)subject;
        if(bossSubject.isSate()){
            System.out.println("WorkerA works");
        }else{
            System.out.println("WorkerA palys telephone");
        }
    }
}
