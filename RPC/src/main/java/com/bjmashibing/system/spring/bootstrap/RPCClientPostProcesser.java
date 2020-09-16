package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.rpc.anotation.RPCInterface;
import com.bjmashibing.system.rpc.client.RPCProxy;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:39
 */
public class RPCClientPostProcesser extends AbstractPostProcessor {

    private RPCProxy rpcProxy;

    public RPCClientPostProcesser(String ip, int port) {
        rpcProxy = new RPCProxy(ip, port);
    }

    @Override
    public boolean filter(Class<?> x) {
        RPCInterface declaredAnnotation = x.getDeclaredAnnotation(RPCInterface.class);
        if (declaredAnnotation != null) {
            add(x.getName(), rpcProxy.proxy(x));
        }
        return false;
    }
}
