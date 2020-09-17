package com.bjmashibing.system.spring.aop;

import com.bjmashibing.system.spring.annotation.Enable;
import com.bjmashibing.system.spring.aop.annotation.Aspect;
import com.bjmashibing.system.spring.aop.annotation.Before;
import com.bjmashibing.system.spring.aop.annotation.EnableAOP;
import com.bjmashibing.system.spring.aop.annotation.Pointcut;
import com.bjmashibing.system.spring.aop.instance.AspectBean;
import com.bjmashibing.system.spring.bootstrap.AbstractPostProcessor;
import com.bjmashibing.system.spring.bootstrap.SpringApplication;

import java.lang.reflect.Field;
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
    private List<Field> inserts = new LinkedList<>();

    @Override
    public boolean filter(Class<?> x) {
        try {
            if (x.isAnnotationPresent(Aspect.class)) {
                Method[] methods = x.getDeclaredMethods();
                AspectBean aspectBean = new AspectBean();

                aspectBean.setObject(x.newInstance());

                for (Method method : methods) {
                    Pointcut p = method.getDeclaredAnnotation(Pointcut.class);
                    if (p != null && p.value().isInterface()) {
                        Class<?> value = p.value();
                        aspectBean.setPointCut(value);
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
        try {
            for (AspectBean aspectBean : list) {
                Class<?> clazz = aspectBean.getPointCut();
                Object o = SpringApplication.getBean(clazz);

//                Class<?> aClass = Class.forName(SpringApplication.interfaceToName.get(clazz.getName()));
//                Field[] declaredFields = aClass.getDeclaredFields();
//                for (Field key : declaredFields) {
//                    if (key.isAnnotationPresent(Autowired.class)) {
//                        key.setAccessible(true);
//                        String name = key.getType().getName();
//                        name = SpringApplication.interfaceToName.get(name);
//                        if (name == null || name.equals("")) {
//                            name = key.getType().getName();
//                        }
//                        Object object = SpringApplication.beans.get(key.getDeclaringClass().getName());
//
//                        key.set(object, SpringApplication.beans.get(name));
//
//                    }
//                }

                Object o1 = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        aspectBean.getBefore().invoke(aspectBean.getObject(), null);
                        return method.invoke(o, args);
                    }
                });
                SpringApplication.beans.put(SpringApplication.interfaceToName.get(clazz.getName()), o1);
                //设置被代理对象  用于注入对象
                SpringApplication.proxyBeans.put(SpringApplication.interfaceToName.get(clazz.getName()), o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
