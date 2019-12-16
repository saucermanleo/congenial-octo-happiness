package com.zy.decorator;
//装饰者模式,使用聚合调用被装饰者的方法,与装饰者方法相结合,可以给一个类一些额外的职责
public class Test {
    public static void main(String[] args) {
        Man m = new Man();
        Xizhuang xizhuang = new Xizhuang(m);
        Lingdai lingdai = new Lingdai(xizhuang);
        Pixie pixie = new Pixie(lingdai);
        pixie.getDescritpion();
    }
}
