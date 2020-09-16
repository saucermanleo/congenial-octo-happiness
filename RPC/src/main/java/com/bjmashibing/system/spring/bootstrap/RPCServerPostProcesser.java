package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.rpc.anotation.RPCInstance;
import com.bjmashibing.system.rpc.anotation.RPCInterface;
import com.bjmashibing.system.rpc.server.Server;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:34
 */
public class RPCServerPostProcesser extends AbstractPostProcessor {
    @Override
    public boolean filter(Class<?> x) {
        RPCInstance declaredAnnotation = x.getDeclaredAnnotation(RPCInstance.class);
        if (declaredAnnotation != null) {
            for (Class genericInterface : x.getInterfaces()) {
                if (genericInterface.isAnnotationPresent(RPCInterface.class)) {
                    try {
                        Object o = x.newInstance();
                        add(genericInterface.getName(), o);
                        add(x.getName(),o);
                        return true;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void lastTodo() {
        Server.beans = getBeansMap();
        new Server(9090);

    }
}
