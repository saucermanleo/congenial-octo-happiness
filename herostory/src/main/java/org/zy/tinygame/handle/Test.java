package org.zy.tinygame.handle;

import org.zy.tinygame.util.ClassReactUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/31 0031 19:57
 */
public class Test {

    public static void main(String[] args) throws IOException {

        Set<Class<?>> classes = ClassReactUtil.listClazz("org", true, x -> {
            if(x.isInterface()){
                return false;
            }
            if (Handle.class.isAssignableFrom(x)) {
                return true;
            } else {
                return false;
            }
        });

        for (Class<?> aClass : classes) {
            Type type = aClass.getGenericInterfaces()[0];
            if(type instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)type;
                System.out.println(parameterizedType.getActualTypeArguments()[0]);
            }

        }
        //classes.forEach(System.out::println);


//        Set<BeanTest> set = new HashSet<>();
//
//        int[] arrays = new int[]{1,2,3,4,5};
//        int[] arrays1 = new int[]{1,2,3,4,5};
//        int[] arrays2 = new int[]{1,2,3,4,5};
//        int[] arrays3 = new int[]{1,2,3,4,5};
//
//        for (int array : arrays) {
//            for (int array1 : arrays) {
//                for (int array2 : arrays) {
//                    for (int array3 : arrays) {
//                        BeanTest t = new BeanTest();
//                        cast(array,t);
//                        cast(array1,t);
//                        cast(array2,t);
//                        cast(array3,t);
//                        set.add(t);
//                    }
//                }
//            }
//        }
//
//        for (BeanTest beanTest : set) {
//            System.out.println(beanTest);
//        }
//        System.out.println(set.size());


    }

    static public void cast(int x, BeanTest t) {
        if (x == 1) {
            t.setVeryGood(t.getVeryGood() + 1);
        }
        if (x == 2) {
            t.setGood(t.getGood() + 1);
        }
        if (x == 3) {
            t.setBad(t.getBad() + 1);
        }
        if (x == 4) {
            t.setVeryBad(t.getVeryBad() + 1);
        }
        if (x == 5) {
            t.setNoResult(t.getNoResult() + 1);
        }
    }

}
