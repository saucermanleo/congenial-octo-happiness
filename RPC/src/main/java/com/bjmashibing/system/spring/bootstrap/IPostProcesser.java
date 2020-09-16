package com.bjmashibing.system.spring.bootstrap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:11
 */
public interface IPostProcesser {
    boolean filter(Class<?> x);

    void process(Class<?> x);
}
