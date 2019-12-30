package com.zy.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/23 0023 11:35
 */
public class Caretaker {
    private List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento){
        mementos.add(memento);
    }

    public Memento get(int index){
        return mementos.get(index);
    }
}
