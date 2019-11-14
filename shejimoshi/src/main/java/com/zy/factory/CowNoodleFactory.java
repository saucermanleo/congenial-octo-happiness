package com.zy.factory;

import com.zy.simplefactory.CowNoodels;
import com.zy.simplefactory.INoodles;

public class CowNoodleFactory implements INoodlesFactory{
    @Override
    public INoodles cookNodles() {
        return new CowNoodels();
    }
}
