package com.bjmashibing.system.rpc.service;

import com.bjmashibing.system.rpc.anotation.RPCInstance;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:53
 */
@RPCInstance
public class Baoma implements Car {
    @Override
    public int getKg() {
        return 10;
    }

    @Override
    public String say(String hello) {
        return hello;
    }

    @Override
    public Person getOwn() {
        Person person = new Person();
        person.setAge(5);
        person.setBirthiday("3");
        person.setName("zy");
        return person;
    }
}
