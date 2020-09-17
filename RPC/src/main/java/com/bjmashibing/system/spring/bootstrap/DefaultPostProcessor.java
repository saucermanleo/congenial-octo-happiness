package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.spring.annotation.Component;

import java.lang.reflect.Field;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:17
 */
public class DefaultPostProcessor extends AbstractPostProcessor {
    @Override
    public boolean filter(Class<?> x) {
        if (x.isAnnotationPresent(Component.class)) {
            try {
                add(x.getName(), x.newInstance());
                return true;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    @Override
    public void lastTodo() {
        for (Field key : SpringApplication.list) {
            String name = key.getType().getName();
            name = SpringApplication.interfaceToName.get(name);
            if (name == null || name.equals("")) {
                name = key.getType().getName();
            }
            try {
                key.set(SpringApplication.beans.get(key.getDeclaringClass().getName()), SpringApplication.beans.get(name));
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
