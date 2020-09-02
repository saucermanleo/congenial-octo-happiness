package org.zy.tinygame.handle;

import org.zy.tinygame.util.ClassReactUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 10:35
 */
public class HandleFactory {

    private static final Map<Class, Handle<?>> map = new HashMap<>();

    static {
        try {
            Set<Class<?>> classes = ClassReactUtil.listClazz("org", true, x -> {
                if (x.isInterface()) {
                    return false;
                }
                if (Handle.class.isAssignableFrom(x)) {
                    return true;
                } else {
                    return false;
                }
            });


            for (Class<?> aClass : classes) {
                Type type = aClass.getGenericInterfaces()[0];
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    map.put((Class) parameterizedType.getActualTypeArguments()[0], (Handle<?>) aClass.newInstance());
                }

            }
            for (Map.Entry<Class, Handle<?>> classHandleEntry : map.entrySet()) {
                System.out.println(classHandleEntry.getKey() + "<=====>" + classHandleEntry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private HandleFactory() {
    }

    public static Handle<?> getHandle(Object msg) {
        return map.get(msg.getClass());
    }
}
