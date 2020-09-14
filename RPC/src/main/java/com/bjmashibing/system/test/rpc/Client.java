package com.bjmashibing.system.test.rpc;

import com.bjmashibing.system.test.rpc.client.RPCFactory;
import com.bjmashibing.system.test.rpc.service.Car;

import java.io.IOException;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:36
 */
public class Client {
    public static void main(String[] args) throws IOException {
        RPCFactory rpcFactory = new RPCFactory(9090,"localhost",Client.class);
        Car car = rpcFactory.getRPCInstance(Car.class);

        for(int i = 0 ;i<50;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0 ;i<100;i++){
                        System.out.println(car.say("hello rpc"));
                        System.out.println(car.getOwn().getAge());
                    }
                }
            }).start();
        }

    }

}
