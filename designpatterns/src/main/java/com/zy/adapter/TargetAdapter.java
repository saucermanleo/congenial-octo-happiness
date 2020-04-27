package com.zy.adapter;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 17:15
 */
public class TargetAdapter extends AdapteeImpl implements Target{
    @Override
    public void request() {
        this.specificRequest();
    }
}
