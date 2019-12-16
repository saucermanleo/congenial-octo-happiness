package com.zy.factory;

import com.zy.simplefactory.INoodles;
import com.zy.simplefactory.LzNoodles;

public class LZNoodleFactory implements INoodlesFactory {
    @Override
    public INoodles cookNodles() {
        return new LzNoodles();
    }
}
