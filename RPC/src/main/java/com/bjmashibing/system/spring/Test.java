package com.bjmashibing.system.spring;

import com.bjmashibing.system.spring.annotation.Component;
import com.bjmashibing.system.spring.bootstrap.SpringApplication;
import com.bjmashibing.system.spring.service.IIHello;
import com.bjmashibing.system.spring.service.World;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 10:32
 */

public class Test {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Test.class);
        springApplication.start();
        World bean = SpringApplication.getBean(World.class);
        IIHello hello = SpringApplication.getBean(IIHello.class);
        bean.test();
        hello.test();
    }
}
