package com.bjmashibing.system.spring.service;

import com.bjmashibing.system.spring.aop.annotation.Aspect;
import com.bjmashibing.system.spring.aop.annotation.Before;
import com.bjmashibing.system.spring.aop.annotation.Pointcut;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 12:23
 */
@Aspect
public class TestAop {
    @Before
    public void before(){
        System.out.println("test before");
    }

    @Pointcut(IHello.class)
    public void pointCut(){

    }
}