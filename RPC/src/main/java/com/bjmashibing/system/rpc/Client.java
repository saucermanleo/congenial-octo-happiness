package com.bjmashibing.system.rpc;

import com.bjmashibing.system.rpc.client.EnableRPCClient;
import com.bjmashibing.system.rpc.service.Car;
import com.bjmashibing.system.rpc.service.IHello;
import com.bjmashibing.system.spring.aop.annotation.EnableAOP;
import com.bjmashibing.system.spring.bootstrap.SpringApplication;

import java.io.IOException;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:36
 */
@EnableRPCClient
@EnableAOP
public class Client {
    public static void main(String[] args) throws IOException {
/*        RPCFactory rpcFactory = new RPCFactory(9090,"localhost",Client.class);
        Car car = rpcFactory.getRPCInstance(Car.class);*/

        SpringApplication springApplication = new SpringApplication(Client.class);
        springApplication.start();
        Car car = SpringApplication.getBean(Car.class);


        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(car.say("hello rpc"));
                        System.out.println(car.getOwn().getAge());
                    }
                }
            }).start();
        }

    }

}
