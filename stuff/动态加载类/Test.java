package com.zdww.eemp.gov.logistics.biz.internal.cloud.building;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author : 生态环境-张阳
 * @date : 2020/6/15 0015 11:31
 */
public class Test {

    public static volatile long lastTime;

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String name = "cn.lijie.MyTest";
        File file;
        while (true) {
            file = new File("d:/", name.replace(".", "/").concat(".class"));
            long lastTime = file.lastModified();
            if (lastTime != Test.lastTime) {
                Test.lastTime = lastTime;
                MyClassLoader myClassLoader = new MyClassLoader();
                Class<?> a1 = myClassLoader.loadClass("cn.lijie.MyTest");
                Object a = a1.newInstance();
                Method test = a1.getMethod("show");
                test.invoke(a);
            } else {
                try {
                    TimeUnit.SECONDS.sleep(4L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

