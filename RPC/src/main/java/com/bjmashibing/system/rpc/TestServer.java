package com.bjmashibing.system.rpc;

import com.bjmashibing.system.spring.annotation.EnableRPCServer;
import com.bjmashibing.system.spring.bootstrap.SpringApplication;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:37
 */
@EnableRPCServer
public class TestServer {
    public static void main(String[] args) {
/*        Server server = new Server(9090, TestServer.class);
        server.start();*/

        SpringApplication springApplication = new SpringApplication(TestServer.class);
        springApplication.start();
    }
}
