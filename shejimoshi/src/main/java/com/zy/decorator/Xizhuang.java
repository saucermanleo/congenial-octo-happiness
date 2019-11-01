package com.zy.decorator;

public class Xizhuang implements Dcorator{

    private Person person;

    public Xizhuang(Person person) {
        this.person = person;
    }

    @Override
    public void getDescritpion() {
        person.getDescritpion();
        System.out.print("西装");
    }
}
