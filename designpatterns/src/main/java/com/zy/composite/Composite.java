package com.zy.composite;

import java.util.ArrayList;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/28 0028 11:36
 */
public class Composite implements School {
    private ArrayList<School> schools = new ArrayList<>();


    @Override
    public void operation() {
        schools.forEach(School::operation);
    }

    @Override
    public void add(School school) {
        schools.add(school);
    }

    @Override
    public void remove(School school) {
        schools.remove(school);
    }
}
