package com.bjmashibing.system.test.rpc.client;

import com.bjmashibing.system.test.rpc.anotation.RPCInterface;
import com.bjmashibing.system.test.rpc.util.ClassReactUtil;

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

    public RPCFactory(int port, String ip) {
        this.port = port;
        this.ip = ip;
        init();
    }

    private void init() {
        try {
            RPCProxy rpcProxy = new RPCProxy(ip, port);
            String name = RPCFactory.class.getPackage().getName();
            name = name.substring(0, name.lastIndexOf("."));
            System.out.println(name);
            Set<Class<?>> classes = ClassReactUtil.listClazz(name, true, x -> {
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
