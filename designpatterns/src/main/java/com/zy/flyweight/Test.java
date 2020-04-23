package com.zy.flyweight;

import java.sql.SQLOutput;

/**
 * 享元模式
 * 1）String的常量池即用到了享元模式，所以为true。他首先检查常量池中是否有str这个值，若有则直接指向，不会重新创建String对象。（共享），但是每次都new String的话是不行的，因为jvm遇到new指令后就会创建一个对象出来，无法沿用常量池。
 *
 * （2）（3）（4）（5）（6）（7）是因为Integer（Long、Byte等）内部维护着一个Cache，用于共享。可以查看Integer的源码，如下：（Long、Byte也是一样的）
 *
 *
 * Integer享元模式源码
 *
 * 可发现，若值在-128~127之间，是可以直接从Cache中获取的，好比String常量池，直接从池中获取，无需new。若不再这范围内，才会去new Integer(i)返回新对象。否则共享。所以享元模式来减少对象数目，达到优化还是很牛的。
 * @author : 生态环境-张阳
 * @date : 2020/4/23 0023 16:34
 */
public class Test {
    public static void main(String[] args) {
        FlyWeight test = FlyWeightFactory.factory("test");
        FlyWeight test2 = FlyWeightFactory.factory("test");
        System.out.println(test == test2);
    }
}
