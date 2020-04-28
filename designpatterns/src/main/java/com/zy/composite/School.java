package com.zy.composite;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/28 0028 11:32
 */
public interface School {
    void operation();

    void add(School school);

    void remove(School school);
}
