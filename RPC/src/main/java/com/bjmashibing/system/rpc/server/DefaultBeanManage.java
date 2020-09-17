package com.bjmashibing.system.rpc.server;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 17:14
 */
public class DefaultBeanManage implements BeansMange {

    public static ConcurrentHashMap<String, Object> beans = new ConcurrentHashMap();



    @Override
    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz.getName());
    }

    @Override
    public <T> void put(Class<T> calzz, T t) {
        beans.put(calzz.getName(),t);
    }
}
