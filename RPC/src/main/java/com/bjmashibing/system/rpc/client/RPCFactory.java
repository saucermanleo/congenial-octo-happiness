package com.bjmashibing.system.rpc.client;

import com.bjmashibing.system.rpc.anotation.RPCInterface;
import com.bjmashibing.system.rpc.util.ClassReactUtil;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/11 0011 17:52
 */
public class RPCFactory {

    public static ConcurrentHashMap<String, Object> beans = new ConcurrentHashMap();
    private int port;
    private String ip;

    public RPCFactory(int port, String ip,Class<?> clazz) {
        this.port = port;
        this.ip = ip;
        this.init(clazz);
    }

    private void init(Class<?> clazz) {
        try {
            RPCProxy rpcProxy = new RPCProxy(ip, port);
            Set<Class<?>> classes = ClassReactUtil.listClazz(clazz, true, x -> {
                RPCInterface declaredAnnotation = x.getDeclaredAnnotation(RPCInterface.class);
                if (declaredAnnotation != null) {
                    return true;
                }
                return false;
            });

            for (Class<?> aClass : classes) {
                beans.put(aClass.getName(), rpcProxy.proxy(aClass));
            }

            System.out.println(RPCFactory.beans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T getRPCInstance(Class<T> calzz) {
        return (T) beans.get(calzz.getName());
    }


}
