package com.zy.simplefactory;

public class SimpleNoodelFactory {

    public static final int TYPE_LZ = 1;
    public static final int TYPE_PAO = 2;
    public static final int TYPE_COW = 3;


    public static INoodles cookNoodels(int type){
        if(type ==TYPE_LZ){
            return new LzNoodles();
        }else if(type == TYPE_PAO){
            return new PaoNoodles();
        }else if(type == TYPE_COW){
            return new CowNoodels();
        }
        return null;
    }
}
