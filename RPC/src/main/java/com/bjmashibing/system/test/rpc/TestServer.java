package com.bjmashibing.system.test.rpc;

import com.bjmashibing.system.test.rpc.server.Server;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/29 0029 11:37
 */
public class TestServer {
    public static void main(String[] args) {
        Server server = new Server(9090,TestServer.class);
        try {
            server.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
