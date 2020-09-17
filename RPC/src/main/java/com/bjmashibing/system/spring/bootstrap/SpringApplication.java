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
            //默认自带依赖注入处理器添加
            postProcesses.add(new DefaultPostProcessor());

            //扫描Enable并添加配置了Enablexxx在启动类上的postprocessor
            ClassReactUtil.listClazz("", true, (x) -> {
                new EnablePostProcessor().process(x);
                return false;
            });

            //扫描并执行所有postprocessor
            ClassReactUtil.listClazz(clazz, true, (x) -> {
                for (IPostProcessor postProcessor : postProcesses) {
                    postProcessor.process(x);
                }
                return false;
            });

            //执行扫描后的操作
            for (IPostProcessor postProcessor : postProcesses) {
                postProcessor.lastTodo();
            }
        } catch (IOException e) {
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
