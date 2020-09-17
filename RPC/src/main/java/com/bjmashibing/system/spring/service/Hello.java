package com.bjmashibing.system.spring.service;

import com.bjmashibing.system.spring.annotation.Autowired;
import com.bjmashibing.system.spring.annotation.Component;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 10:22
 */
@Component
public class Hello implements IHello{

    @Autowired
    private World world;

    @Override
    public void sayHello(){
        System.out.println("hello");
    }


    @Override
    public void test() {
        world.hello();
        System.out.println("test");
    }
}
