package com.zy.factory;

import com.zy.simplefactory.INoodles;

//扩展性大大提高，当需要添加一种商品时，只需要添加生产该商品的工厂，并让其实现生产工厂接口即可
public class Test {
    public static void main(String[] args) {
        INoodlesFactory factory = new LZNoodleFactory();
        INoodles noodles = factory.cookNodles();
        noodles.desc();
    }
}
