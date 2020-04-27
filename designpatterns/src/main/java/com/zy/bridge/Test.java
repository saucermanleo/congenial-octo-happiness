package com.zy.bridge;

/**
 * 把抽象化与实现解耦. 通过聚合来组合  这样对象数量就减少了
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 15:16
 */
public class Test {
    public static void main(String[] args) {
        LargePen largePen = new LargePen(new RedColor());
        largePen.operation();
    }
}
