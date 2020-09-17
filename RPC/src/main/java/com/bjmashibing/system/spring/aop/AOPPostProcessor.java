package com.bjmashibing.system.spring.aop;

import com.bjmashibing.system.spring.annotation.Enable;
import com.bjmashibing.system.spring.aop.annotation.Aspect;
import com.bjmashibing.system.spring.aop.annotation.Before;
import com.bjmashibing.system.spring.aop.annotation.EnableAOP;
import com.bjmashibing.system.spring.aop.annotation.Pointcut;
import com.bjmashibing.system.spring.aop.instance.AspectBean;
import com.bjmashibing.system.spring.bootstrap.AbstractPostProcessor;
import com.bjmashibing.system.spring.bootstrap.SpringApplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 10:59
 */
@Enable(EnableAOP.class)
public class AOPPostProcessor extends AbstractPostProcessor {

    private List<AspectBean> list = new LinkedList<>();

    @Override
    public boolean filter(Class<?> x) {
        try {
            if (x.isAnnotationPresent(Aspect.class)) {
                Method[] methods = x.getDeclaredMethods();
                AspectBean aspectBean = new AspectBean();

                aspectBean.setObject(x.newInstance());

                for (Method method : methods) {
                    Pointcut p = method.getDeclaredAnnotation(Pointcut.class);
                    if (p != null&& p.value().isInterface()) {
                        aspectBean.setPointCut(p.value());
                    } else if (method.isAnnotationPresent(Before.class)) {
                        aspectBean.setBefore(method);
                    }
                }
                list.add(aspectBean);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void lastTodo() {
        for (AspectBean aspectBean : list) {
            Class<?> clazz = aspectBean.getPointCut();
            Object o = SpringApplication.getBean(clazz);
            Object o1 = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    aspectBean.getBefore().invoke(aspectBean.getObject(), null);
                    return method.invoke(o, args);
                }
            });

            SpringApplication.beans.put(SpringApplication.interfaceToName.get(clazz.getName()), o1);
        }
    }
}
