package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.rpc.util.ClassReactUtil;

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
            List<IPostProcesser> postProcesses = new LinkedList<>();
            postProcesses.add(new DefaultPostProcessor());
            ClassReactUtil.listClazz(clazz, true, (x) -> {
                for (IPostProcesser postProcesser : postProcesses) {
                    postProcesser.filter(x);
                }
                return false;
            });
            for (Field key : list) {
                String name;
                if (key.getType().isInterface()) {
                    name = interfaceToName.get(key.getType().getName());
                } else {
                    name = key.getType().getName();
                }
                key.set(beans.get(key.getDeclaringClass().getName()), beans.get(name));
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
        if (t.isInterface()) {
            return (T) beans.get(interfaceToName.get(t.getName()));
        } else {
            return (T) beans.get(t.getName());
        }
    }
}
