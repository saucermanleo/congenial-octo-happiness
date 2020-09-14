package com.bjmashibing.system.test.rpc.service;

import java.io.Serializable;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 15:00
 */
public class Person implements Serializable {
    private String name;
    private String birthiday;
    private int age;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthiday() {
        return birthiday;
    }

    public void setBirthiday(String birthiday) {
        this.birthiday = birthiday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthiday='" + birthiday + '\'' +
                ", age=" + age +
                '}';
    }
}
