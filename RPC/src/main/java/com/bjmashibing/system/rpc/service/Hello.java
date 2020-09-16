package com.bjmashibing.system.rpc.service;

import com.bjmashibing.system.spring.annotation.Component;


/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 10:22
 */
@Component
public class Hello implements IHello{

    @Override
    public void sayHello(){
        System.out.println("hello");
    }


    @Override
    public void test() {
        System.out.println("test");
    }
}
