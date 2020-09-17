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
    public static List<IPostProcessor> postProcesses = new LinkedList<>();
    public static Class<?> clazz;


    public SpringApplication(Class<?> c) {
        clazz = c;
    }

    public void start() {
        try {
            postProcesses.add(new DefaultPostProcessor());
            //扫描Enable并添加postprocessor
            ClassReactUtil.listClazz("", true, (x) -> {
                new EnablePostProcessor().filter(x);
                return false;
            });
            //扫描并执行postprocessor
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
