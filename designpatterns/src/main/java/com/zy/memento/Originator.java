package com.zy.memento;

import com.zy.mediator.Mediator;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/23 0023 11:27
 */
public class Originator {
    private String name;
    private int age;
    private int weight;

    public Originator(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Memento createMemento(){
        return new  Memento(this);
    }

    public void setMemento(Memento memento){
        age = memento.getAge();
        weight = memento.getWeight();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Originator{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
