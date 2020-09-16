package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.rpc.util.ClassReactUtil;
import com.bjmashibing.system.spring.annotation.EnableRPCClient;
import com.bjmashibing.system.spring.annotation.EnableRPCServer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 9:41
 */
public class SpringApplication {

    public static List<Field> list = new LinkedList<>();
    public static ConcurrentHashMap<String, Object> beans = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, String> interfaceToName = new ConcurrentHashMap<>();
    private Class<?> clazz;


    public SpringApplication(Class<?> calzz) {
        this.clazz = calzz;
    }

    public void start() {


        try {
            List<IPostProcessor> postProcesses = new LinkedList<>();
            postProcesses.add(new DefaultPostProcessor());
            if (clazz.isAnnotationPresent(EnableRPCClient.class)) {
                postProcesses.add(new RPCClientPostProcesser("localhost", 9090));
            }
            if (clazz.isAnnotationPresent(EnableRPCServer.class)) {
                postProcesses.add(new RPCServerPostProcesser());
            }

            ClassReactUtil.listClazz(clazz, true, (x) -> {
                for (IPostProcessor postProcessor : postProcesses) {
                    postProcessor.process(x);
                }
                return false;
            });
            for (Field key : list) {
                String name = key.getType().getName();
                name = interfaceToName.get(name);
                if (name == null || name.equals("")) {
                    name = key.getType().getName();
                }
                key.set(beans.get(key.getDeclaringClass().getName()), beans.get(name));
            }

            for (IPostProcessor postProcessor : postProcesses) {
                postProcessor.lastTodo();
            }

            System.out.println(list);
            list = null;
            System.out.println(beans);
            System.out.println(interfaceToName);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getBean(Class<T> t) {
        String name = interfaceToName.get(t.getName());
        if (name == null || name.equals("")) {
            name = t.getName();
        }
        return (T) beans.get(name);

    }
}
