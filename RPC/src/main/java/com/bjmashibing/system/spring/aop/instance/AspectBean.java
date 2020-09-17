package com.bjmashibing.system.spring.aop.instance;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 11:24
 */
@Data
public class AspectBean {
    private Class<?> pointCut;
    private Method before;
    private Object object;
}
