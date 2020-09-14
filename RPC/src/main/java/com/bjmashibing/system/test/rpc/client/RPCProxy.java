package com.bjmashibing.system.test.rpc.client;

import com.bjmashibing.system.test.rpc.request.Parameter;
import com.bjmashibing.system.test.rpc.request.SmartCarProtocol;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/28 0028 15:56
 */
public class RPCProxy {

    public RPCProxy(String ip,int port) {
        connectionPools = new ConnectionPool[1];
        connectionPools[0] = new ConnectionPool(100,new InetSocketAddress(ip,port));
    }

    private  ConnectionPool[] connectionPools;
    public static  ConcurrentHashMap<Long,Runnable> map = new ConcurrentHashMap<>();

    public  <T>T proxy(Class<T> clazz){
        T o = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                String className = clazz.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                Parameter parameter = new Parameter(className,name,parameterTypes);
                parameter.setCalzz(clazz);
                parameter.setArgs(args);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(parameter);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                SmartCarProtocol smartCarProtocol = new SmartCarProtocol(bytes.length,bytes);
                NioSocketChannel connection = connectionPools[0].getConnection();
                Invocation invocation = new Invocation(Thread.currentThread());
                map.put(parameter.getRequestId(), invocation);
                connection.writeAndFlush(smartCarProtocol);

                LockSupport.park();
                connectionPools[0].close(connection);
                return invocation.getResult();
            }
        });
        return o;
    }

}

class Invocation implements Runnable{
    private Thread thread;
    private Object result;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Invocation(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        LockSupport.unpark(thread);
    }
}