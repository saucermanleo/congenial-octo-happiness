package com.zy.subjectobserver;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

//观察者模式-使用聚合,循环通知每个被观察者完成逻辑,通知时把主题传给被通知者
//jdk 有实现 分别为:
public class Test {
    public static void main(String[] args) {

        Class s = Observer.class;
        Class ob = Observable.class;

        Class eventListener = EventListener.class;
        Class eventObject = EventObject.class;


        BossSubject bossSubject = new BossSubject();
        bossSubject.register(new WorkerAObserver());
        bossSubject.register(new WorkerBObserver());
        bossSubject.setSate(true);
        bossSubject.setSate(false);
        bossSubject.setSate(true);
    }
}
