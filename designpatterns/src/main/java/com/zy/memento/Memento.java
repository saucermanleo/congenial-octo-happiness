package com.zy.memento;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/23 0023 11:29
 */
public class Memento {
    private int age;
    private int weight;

    public Memento(Originator originator) {
        this.age = originator.getAge();
        this.weight = originator.getWeight();
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
}
