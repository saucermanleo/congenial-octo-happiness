package com.bjmashibing.system.test.netty;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/25 0025 13:08
 */
public class Test {
    public static void main(String[] args) {
        //NettyServer nettyServer = new NettyServer();
        NettyClient nettyClient = new NettyClient();
        try {
            //nettyServer.open();
            nettyClient.open();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
