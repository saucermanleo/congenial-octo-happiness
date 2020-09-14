package com.bjmashibing.system.test.rpc.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/11 0011 16:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RPCInstance {
}
