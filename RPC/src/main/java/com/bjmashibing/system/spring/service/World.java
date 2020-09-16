package com.bjmashibing.system.spring.service;

import com.bjmashibing.system.spring.annotation.Autowired;
import com.bjmashibing.system.spring.annotation.Component;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 10:23
 */
@Component
public class World {

    @Autowired
    private IHello hello;

    public void test(){
        hello.sayHello();
    }
}
