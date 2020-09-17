package com.bjmashibing.system.spring.annotation;

import java.lang.annotation.*;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 10:01
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enable {
    Class<? extends Annotation> value();
}
