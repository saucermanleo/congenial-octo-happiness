//package com.bjmashibing.system.spring.util;
//
//import com.zdww.eemp.common.log.internal.annotations.OperLog;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.core.type.AnnotationMetadata;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Map;
//
///**
// * 使用父接口的swagger信息填充OperLog后处理器
// * @author : 生态环境-张阳
// * @date : 2020/10/10 0010 14:52
// */
//@Component
//public class LogBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
//            BeanDefinition beanDef = beanFactory.getBeanDefinition(beanDefinitionName);
//            AnnotationMetadata metadata;
//            if (beanDef instanceof AnnotatedBeanDefinition) {
//                metadata = ((AnnotatedBeanDefinition) beanDef).getMetadata();
//                if (metadata.isAnnotated(RestController.class.getName())) {
//
//                    Method[] declaredMethods;
//                    try {
//                        Class<?> clazz = Class.forName(metadata.getClassName());
//                        Class<?>[] interfaces = clazz.getInterfaces();
//                        declaredMethods = clazz.getDeclaredMethods();
//                        for (Method declaredMethod : declaredMethods) {
//                            for (Class<?> i : interfaces) {
//                                if (i.isAnnotationPresent(Api.class)) {
//                                    Api annotation = i.getAnnotation(Api.class);
//                                    Method declaredMethod1;
//                                    try {
//                                        declaredMethod1 = i.getDeclaredMethod(declaredMethod.getName(), declaredMethod.getParameterTypes());
//                                    } catch (NoSuchMethodException e) {
//                                        continue;
//                                    }
//                                    if (declaredMethod1.isAnnotationPresent(ApiOperation.class) && declaredMethod.isAnnotationPresent(OperLog.class)) {
//                                        ApiOperation declaredAnnotation = declaredMethod1.getDeclaredAnnotation(ApiOperation.class);
//                                        OperLog operLog = declaredMethod.getAnnotation(OperLog.class);
//
//                                        InvocationHandler h = Proxy.getInvocationHandler(operLog);
//                                        // 获取 AnnotationInvocationHandler 的 memberValues 字段
//                                        Field hField = h.getClass().getDeclaredField("memberValues");
//                                        // 因为这个字段事 private final 修饰，所以要打开权限
//                                        hField.setAccessible(true);
//                                        // 获取 memberValues
//                                        Map memberValues = (Map) hField.get(h);
//
//                                        if (StringUtils.isEmpty(memberValues.get("operModul"))) {
//                                            memberValues.put("operModul", annotation.value());
//                                        }
//
//                                        if (StringUtils.isEmpty(memberValues.get("operDesc"))) {
//                                            memberValues.put("operDesc", declaredAnnotation.value());
//                                        }
//
//                                    }
//                                }
//
//                            }
//                        }
//                    } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//
//            }
//
//        }
//    }
//}
