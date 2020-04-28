package com.zy.composite;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/28 0028 11:41
 */
public class InternetDepartment extends SchoolAdapter implements School {
    @Override
    public void operation() {
        System.out.println("执行网络部门的职责");
    }
}
