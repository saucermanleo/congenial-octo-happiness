package com.bjmashibing.system.rpc.service;

import com.bjmashibing.system.rpc.anotation.RPCInterface;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/28 0028 15:54
 */
@RPCInterface
public interface Car {
    /**
    * 得到质量
    *
    * @author : 生态环境-张阳
    * @date : 2020/7/28 0028 15:55
    */
    int getKg();

    String say(String hello);

    Person getOwn();

}
