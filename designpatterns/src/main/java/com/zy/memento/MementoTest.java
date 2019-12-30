package com.zy.memento;

/**
 * 不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态
 *
 * @author : 生态环境-张阳
 * @date : 2019/12/23 0023 11:40
 */
public class MementoTest {
    public static void main(String[] args) {
        Originator originator = new Originator("zy", 29, 50);
        Memento memento = originator.createMemento();
        System.out.println(originator);
        Caretaker caretaker = new Caretaker();
        caretaker.addMemento(memento);
        originator.setAge(30);
        originator.setWeight(60);
        System.out.println(originator);
        caretaker.addMemento(originator.createMemento());
        originator.setMemento(caretaker.get(0));
        System.out.println(originator);
    }
}
