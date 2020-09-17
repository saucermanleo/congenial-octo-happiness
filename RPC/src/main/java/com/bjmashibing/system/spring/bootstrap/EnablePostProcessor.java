package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.spring.annotation.Enable;

import java.lang.annotation.Annotation;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/17 0017 10:06
 */
public class EnablePostProcessor extends AbstractPostProcessor {
    @Override
    public boolean filter(Class<?> x) {
        Enable e = x.getDeclaredAnnotation(Enable.class);
        if (e != null) {
            Class<? extends Annotation> value = e.value();
            if (SpringApplication.clazz.isAnnotationPresent(value)) {
                if (IPostProcessor.class.isAssignableFrom(x)) {
                    try {
                        SpringApplication.postProcesses.add((IPostProcessor) x.newInstance());
                    } catch (InstantiationException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
        return false;
    }
}
