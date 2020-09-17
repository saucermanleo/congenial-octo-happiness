package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.spring.annotation.Autowired;
import com.bjmashibing.system.spring.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:11
 */
public abstract class AbstractPostProcessor implements IPostProcessor {


    public void add(String name,Object object){
        SpringApplication.beans.put(name,object);
    }


    public void afterProcess(Class<?> x){
        Field[] declaredFields = x.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                declaredField.setAccessible(true);
                SpringApplication.list.add(declaredField);
            }
        }
        ReflectUtil.getInterface(SpringApplication.interfaceToName,x,x.getName());

    }


    @Override
    public void process(Class<?> x) {
        if(this.filter(x)){
            afterProcess(x);
        }
    }

    @Override
    public void lastTodo() {

    }

    public ConcurrentHashMap<String, Object>  getBeansMap(){
        return SpringApplication.beans;
    }
}
