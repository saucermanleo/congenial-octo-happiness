package com.zy.adapter;

/**
 * 适配器模式
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 17:21
 */
public class Test    {
    public static void main(String[] args) {
        Target targetAdapter = new TargetAdapter();
        targetAdapter.request();
    }
}
