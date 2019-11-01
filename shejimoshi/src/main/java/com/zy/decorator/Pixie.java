package com.zy.decorator;

public class Pixie implements Dcorator{
    private Person person;

    public Pixie(Person person) {
        this.person = person;
    }

    @Override
    public void getDescritpion() {
        person.getDescritpion();
        System.out.print("皮鞋");
    }
}
