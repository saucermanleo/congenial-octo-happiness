package com.bjmashibing.system.spring.bootstrap;

import com.bjmashibing.system.spring.annotation.Component;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/16 0016 15:17
 */
public class DefaultPostProcessor extends AbstractPostProcessor {
    @Override
    public boolean filter(Class<?> x) {
        if (x.isAnnotationPresent(Component.class)) {
            afterProcess(x);
            try {
                add(x.getName(), x.newInstance());
                return true;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;

    }
}
