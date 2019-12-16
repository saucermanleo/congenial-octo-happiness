package com.zy.decorator;

public class Lingdai implements Dcorator {
    private Person person;

    public Lingdai(Person person) {
        this.person = person;
    }

    @Override
    public void getDescritpion() {
        person.getDescritpion();
        System.out.print("领带");
    }
}
