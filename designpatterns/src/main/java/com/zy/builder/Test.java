package com.zy.builder;

public class Test {
    public static void main(String[] args) {
        Person p = new Person.Builder("zy","man").car("autoA6").build();
        System.out.println(p);
    }
}
