package com.zy.flyweight;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/23 0023 16:28
 */
public class ConcreteFlyweight implements FlyWeight {
    private final String intrinsicState;


    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String state) {
        System.out.println(state);
    }
}
