package com.zy.simplefactory;
/*特点
        1 它是一个具体的类，非接口 抽象类。有一个重要的create()方法，利用if或者 switch创建产品并返回。

        2 create()方法通常是静态的，所以也称之为静态工厂。

        缺点
        1 扩展性差（我想增加一种面条，除了新增一个面条产品类，还需要修改工厂类方法）开闭原则：对扩展开放，对修改关闭

        2 不同的产品需要不同额外参数的时候 不支持。*/
public class Test {
    public static void main(String[] args) {
        INoodles noodles = SimpleNoodelFactory.cookNoodels(SimpleNoodelFactory.TYPE_LZ);
        noodles.desc();
    }
}
