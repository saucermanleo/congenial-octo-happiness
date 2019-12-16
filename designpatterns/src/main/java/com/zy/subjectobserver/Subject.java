package com.zy.subjectobserver;

import java.util.LinkedList;
import java.util.List;

public class Subject {
    List<Observer> observers = new LinkedList<>();

    public void register(Observer observer){
        observers.add(observer);
    }

    public void unRegister(Observer observer){
        observers.remove(observer);
    }

    public void notifyUpdate(){
        observers.stream().forEach((x)->x.update(this));
    }

}
