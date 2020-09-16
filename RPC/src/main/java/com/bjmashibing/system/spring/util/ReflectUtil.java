package com.bjmashibing.system.spring.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:22
 */
public class ReflectUtil {
    public static void getInterface(ConcurrentHashMap<String, String> map, Class<?> x, String name){
        Class<?>[] interfaces = x.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            map.put(anInterface.getName(),name);
            Class<?>[] interfaces1 = anInterface.getInterfaces();
            if(interfaces1!=null && interfaces1.length!=0){
                getInterface(map,anInterface,name);
            }

        }
    }
}
