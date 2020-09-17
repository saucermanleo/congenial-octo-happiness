package com.bjmashibing.system.rpc.server;

import com.bjmashibing.system.spring.bootstrap.SpringApplication;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 17:29
 */
public class SpringBeanMange implements BeansMange {




    @Override
    public <T> T getBean(Class<T> clazz) {
        return SpringApplication.getBean(clazz);
    }

    @Override
    public <T> void put(Class<T> calzz, T t) {

    }
}
