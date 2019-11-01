package com.zy.subjectobserver;
//观察者模式-使用聚合,循环通知每个被观察者完成逻辑,通知时把主题传给被通知者
public class Test {
    public static void main(String[] args) {
        BossSubject bossSubject = new BossSubject();
        bossSubject.register(new WorkerAObserver());
        bossSubject.register(new WorkerBObserver());
        bossSubject.setSate(true);
        bossSubject.setSate(false);
        bossSubject.setSate(true);
    }
}
