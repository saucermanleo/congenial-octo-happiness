package com.zy.composite;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/28 0028 11:33
 */
public class SchoolAdapter implements School {
    @Override
    public void operation() {

    }

    @Override
    public void add(School school) {
        System.out.println("不支持此方法");
    }

    @Override
    public void remove(School school) {
        System.out.println("不支持此方法");
    }
}
