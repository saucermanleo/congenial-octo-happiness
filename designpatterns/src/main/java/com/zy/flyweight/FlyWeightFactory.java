package com.zy.flyweight;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/23 0023 16:29
 */
public class FlyWeightFactory {
    private static ConcurrentHashMap<String , FlyWeight> concurrentHashMap = new ConcurrentHashMap<>();

    public static FlyWeight factory(String state){
        FlyWeight flyWeight = concurrentHashMap.get(state);
        if(flyWeight == null){
            flyWeight = new ConcreteFlyweight(state);
            concurrentHashMap.put(state,flyWeight);
        }
        return  flyWeight;
    }
}
