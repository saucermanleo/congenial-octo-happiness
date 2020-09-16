package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.rpc.util.ClassReactUtil;
import com.bjmashibing.system.spring.annotation.Autowired;
import com.bjmashibing.system.spring.annotation.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 9:41
 */
public class SpringApplication {

    private List<Field> list = new LinkedList<>();
    private static ConcurrentHashMap<String, Object> beans = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> interfaceToName = new ConcurrentHashMap<>();
    private Class<?> clazz;


    public SpringApplication(Class<?> calzz) {
        this.clazz = calzz;
    }

    private void getInterface(ConcurrentHashMap<String, String> map,Class<?> x,String name){
        Class<?>[] interfaces = x.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            interfaceToName.put(anInterface.getName(),name);
            Class<?>[] interfaces1 = anInterface.getInterfaces();
            if(interfaces1!=null && interfaces1.length!=0){
                getInterface(map,anInterface,name);
            }

        }
    }

    public void start() {
        try {
            Set<Class<?>> classes = ClassReactUtil.listClazz(clazz, true, (x) -> {
                if (x.isAnnotationPresent(Component.class)) {
                    Field[] declaredFields = x.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        if (declaredField.isAnnotationPresent(Autowired.class)) {
                            declaredField.setAccessible(true);
                            list.add(declaredField);
                        }
                    }
                    getInterface(interfaceToName,x,x.getName());
                    return true;
                }
                return false;
            });
            for (Class<?> aClass : classes) {
                beans.put(aClass.getName(), aClass.newInstance());
            }
            for (Field key : list) {
                String name;
                if(key.getType().isInterface()){
                    name = interfaceToName.get(key.getType().getName());
                }else{
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
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getBean(Class<T> t) {
        if(t.isInterface()){
           return  (T) beans.get(interfaceToName.get(t.getName()));
        }else{
            return (T) beans.get(t.getName());
        }
    }
}
