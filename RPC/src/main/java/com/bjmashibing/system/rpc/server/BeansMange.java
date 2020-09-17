package com.bjmashibing.system.rpc.server;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 17:12
 */
public interface BeansMange {
    <T> T getBean(Class<T> clazz);

    <T> void put(Class<T> calzz , T t );
}
