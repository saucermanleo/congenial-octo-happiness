package com.zy.factory;

import com.zy.simplefactory.INoodles;
import com.zy.simplefactory.PaoNoodles;

public class PaoNoodleFactory implements INoodlesFactory {
    @Override
    public INoodles cookNodles() {
        return new PaoNoodles();
    }
}
