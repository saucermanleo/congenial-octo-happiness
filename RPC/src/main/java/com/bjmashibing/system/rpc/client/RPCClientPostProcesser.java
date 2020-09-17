package com.bjmashibing.system.rpc.client;

import com.bjmashibing.system.rpc.anotation.RPCInterface;
import com.bjmashibing.system.spring.annotation.Enable;
import com.bjmashibing.system.spring.bootstrap.AbstractPostProcessor;
import com.bjmashibing.system.spring.bootstrap.SpringApplication;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:39
 */
@Enable(value = EnableRPCClient.class)
public class RPCClientPostProcesser extends AbstractPostProcessor {

    private RPCProxy rpcProxy;

    public RPCClientPostProcesser() {
        rpcProxy = new RPCProxy("localhost", 9090);
    }

    @Override
    public boolean filter(Class<?> x) {
        RPCInterface declaredAnnotation = x.getDeclaredAnnotation(RPCInterface.class);
        if (declaredAnnotation != null) {
            add(x.getName(), rpcProxy.proxy(x));
            SpringApplication.interfaceToName.put(x.getName(), x.getName());
        }
        return false;
    }
}
